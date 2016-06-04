import java.util.Random;
public class Td {


	    private int qSize;
	    private double gamma;
	    private double lambda;
	    private int iterations;
	    private int states[];
	    private int R[][];
	    private int q[][] = new int[qSize][qSize];
	    private int currentState;
	    
	    public Td(int R[][], int iterations, double gamma, double lambda){	
	    	//input: Rewards, number of iterations, gamma
	    	this.R=R;
	    	this.iterations=iterations;
	    	this.gamma=gamma;
	    	this.lambda=lambda;
	    	
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
	    
	    public void train(){
	        for(int j = 0; j < iterations; j++){
	            for(int i = 0; i < qSize; i++){
	                episode(currentState, i);
	            }
	        }
	        System.out.println("q[i][j] values:");
	        for(int i = 0; i < qSize; i++){
	            for(int j = 0; j < qSize; j++){
	                System.out.print(q[i][j] + ",\t");
	            } 
	            System.out.print("\n");
	        }
	        System.out.print("\n");
	    }

	    private int episode(int currentState, int action){
	        if(currentState==qSize-1) return 100;
	        else{
	    	  int possibleAction = getRandomAction();
	          if(R[currentState][possibleAction]>=0){
	            q[currentState][possibleAction]=
	             (int)(R[currentState][possibleAction] + (gamma *((1-lambda)*maximum(possibleAction)+lambda*(episode(currentState,possibleAction)))));
	            currentState = possibleAction;
	            
	          }
	          return q[currentState][possibleAction];
	    }}
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
	    private int maximum(int State){
	        int winner = 0;
	        boolean foundNewWinner = false;
	        boolean done = false;
	        while(!done){
	            foundNewWinner = false;
	            for(int i=0; i<qSize; i++){
	                if(i!=winner){             // Avoid self-comparison.
	                    if(q[State][i]>q[State][winner]){
	                        winner=i;
	                        foundNewWinner=true;
	                    }
	                }
	            }
	            if(foundNewWinner==false) done = true;
	        }
	        return q[State][winner];
	    }
	}
	
	

