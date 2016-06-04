//potenziato
import java.util.*;

public class QLearning{
    private int qSize;
    private double gamma;
    private int iterations;
    private int states[];
    private int R[][];
    private int q[][] = new int[qSize][qSize];
    private int currentState;
    private int counter;
    private int step;
    private Stack<Integer> statesStack=new Stack<Integer>();
    private Stack<Integer> actionsStack=new Stack<Integer>();
    public QLearning(int R[][], int iterations, double gamma){	
    	//input: Rewards, number of iterations, gamma
    	this.counter=0;
    	this.step=0;
    	this.R=R;
    	this.iterations=iterations;
    	this.gamma=gamma;
    	
    	//bulding data structures
    	this.qSize=R.length;
    	this.states=new int[qSize];
    	this.q=new int[qSize][qSize];
    	
        for(int i=0; i<qSize; i++){
            for(int j=0; j<qSize; j++){
                q[i][j]=0;
            }
        }
        for(int j=0; j<qSize; j++){
            states[j]=j;
        }
        currentState=0;
    }
    public long train(){
    	long time = System.nanoTime();
    	for(int j = 0; j < iterations/2; j++){
            episodeExploring(new Random().nextInt(qSize));
        }
        for(int j = 0; j < iterations/2; j++){
            episodeExploiting(new Random().nextInt(qSize));
        }
        long endTime = System.nanoTime()-time; 
        
        
        System.out.println("q[i][j] values:");
        for(int i = 0; i < qSize; i++){
            for(int j = 0; j < qSize; j++){
                System.out.print(q[i][j] + ",\t");
            } 
            System.out.print("\n");
        }
        System.out.print("\n");
        return endTime;
    }
    private void episodeExploring(int initialState){
   	 currentState = initialState;
     //I hypothesize that the goal state is always the last (qSize-1)
     //Travel from state to state until goal state is reached
     while(currentState!=qSize-1){
     	statesStack.push(currentState);
     	currentState=getRandomAction();
     	actionsStack.push(currentState);
         counter++;
     }
     while(!statesStack.isEmpty()){
     	int state=statesStack.pop();
     	int action=actionsStack.pop();
     	//System.out.println(aux);
         if(R[state][action]>=0){
         	q[state][action]=(int)(R[state][action]+(gamma*maximum(action)));
         }
         
     }
    }  
    private void episodeExploiting(int initialState){
    	 currentState = initialState;
         //I hypothesize that the goal state is always the last (qSize-1)
         //Travel from state to state until goal state is reached
         while(currentState!=qSize-1){
         	statesStack.push(currentState);
         	currentState=getMaximumNextAction(currentState);
         	actionsStack.push(currentState);
             counter++;
         }
         while(!statesStack.isEmpty()){
         	int state=statesStack.pop();
         	int action=actionsStack.pop();
         	//System.out.println(aux);
             if(R[state][action]>=0){
             	q[state][action]=(int)(R[state][action]+(gamma*maximum(action)));
             }
             
         }
        
    }
    private int getRandomAction(){
        int action = 0;
        boolean choiceIsValid = false;
        while(choiceIsValid == false){
            action = new Random().nextInt(qSize);
            if(R[currentState][action]>-1){
                choiceIsValid = true;
            }
        }
        return action;
    }
    private int maximum(int state){
        int aux=0;
        for(int i=0; i<qSize; i++){
     	   if(q[state][i]>aux){
     		   aux=q[state][i];
     	   }
        }
        return aux;
    }
    private int getMaximumNextAction(int state){
        int aux=0;
        int win=0;
        for(int i=0; i<qSize; i++){
     	   if(q[state][i]>aux){
     		   aux=q[state][i];
     		   win=i;
     	   }
        }
        return win;
    }    
    public double test(){
        int state=0;
    	for(int i = 0; i<qSize; i++){
        	state=i;
        	System.out.println("");
        	while(state!=qSize-1){
    		    state=getMaximumNextAction(state);
    		    System.out.print(" | "+state);
    		    step++;
    	    }
        	System.out.println("");
        }
    	return (double)step/qSize;
    }  
    public int getNumberStates(){
    	return this.counter;
    }
    public int getIterations(){
    	return this.iterations;
    }
    public int getStep(){
    	return this.step;
    }
    public double getDiscountFactor(){
    	return this.gamma;
    }
}