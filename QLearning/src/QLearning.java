//d
import java.util.*;

public class QLearning{
    private int qSize;
    private double gamma;
    private int episodesExploring;
    private int episodesExploiting;
    private int states[];
    private int R[][];
    private int q[][] = new int[qSize][qSize];
    private int currentState;
    private int counter;
    private int step;
    private Stack<Integer> statesStack=new Stack<Integer>();
    private Stack<Integer> actionsStack=new Stack<Integer>();
    public enum mode {WITHSTACK, WITHOUTSTACK}
    
    
    public QLearning(int R[][], int episodesExploring, int episodesExploiting, double gamma){	
    	//input: rewards, number of iterations in exploring, number of iterations in exploiting, gamma.
    	this.counter=0;
    	this.step=0;
    	this.R=R;
    	this.episodesExploring=episodesExploring;
    	this.episodesExploiting=episodesExploiting;
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
    public long train(mode e){             // WITHSTACK or WITHOUTSTACK
    	long time=0;
    	long endTime=0;
    	//episodes with stack:
    	if(e==mode.WITHSTACK){
    	    time = System.nanoTime();
    	    for(int j = 0; j < episodesExploring; j++){
                episodeExploringWithStack(new Random().nextInt(qSize));
            }
    	    for(int j = 0; j < episodesExploiting; j++){
                episodeExploitingWithStack(new Random().nextInt(qSize));
            }
            endTime = System.nanoTime()-time; 
    	}   
    	//episodes without stack
    	if(e==mode.WITHOUTSTACK){
     	    time = System.nanoTime();
     	    for(int j = 0; j < episodesExploring; j++){
                episodeExploringWithoutStack(new Random().nextInt(qSize));
             }
     	     for(int j = 0; j < episodesExploiting; j++){
                episodeExploitingWithoutStack(new Random().nextInt(qSize));
             }
             endTime = System.nanoTime()-time; 
     	}
    	//PRINT THE Q-FUNCTION
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
    private void episodeExploringWithStack(int initialState){
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
     	   if(R[state][action]>=0){
         	  q[state][action]=(int)(R[state][action]+(gamma*maximum(action)));
           }   
        }
    }   
    private void episodeExploitingWithStack(int initialState){
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
           if(R[state][action]>=0){
              q[state][action]=(int)(R[state][action]+(gamma*maximum(action)));
           }   
        } 
    }
    private void episodeExploringWithoutStack(int initialState){
        currentState = initialState;
        //I hypothesize that the goal state is always the last (qSize-1)
        //Travel from state to state until goal state is reached
        while(currentState!=qSize-1){
           int possibleAction = 0;
           possibleAction = getRandomAction();
           if(R[currentState][possibleAction]>=0){
              q[currentState][possibleAction]=
              (int)(R[currentState][possibleAction] + (gamma * maximum(possibleAction)));
              currentState = possibleAction;
           }
           counter++;
        }  
    }  
    private void episodeExploitingWithoutStack(int initialState){
        currentState = initialState;
        //I hypothesize that the goal state is always the last (qSize-1)
        //Travel from state to state until goal state is reached
        while(currentState!=qSize-1){
            int possibleAction = 0;
            possibleAction = getMaximumNextAction(currentState);
            if(R[currentState][possibleAction]>=0){
                q[currentState][possibleAction]=
                 (int)(R[currentState][possibleAction] + (gamma * maximum(possibleAction)));
                currentState = possibleAction;
            }
            counter++;
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
    public int getStep(){
    	return this.step;
    } 
}
