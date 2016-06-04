//potenziato


public class Training {
    public static void main(String[] args){
        //columns actions
    	//rows states
    	int R[][] = new int[][]{
    			{-1, 0, 0, -1, -1, -1, -1, -1, -1, -1},
        		{0, -1, -1, 0, 0, -1, -1, -1, -1, 100},
        		{0, -1, -1, 0, -1, -1, -1, 0, -1, -1},
        		{-1, 0, 0, -1,  0,  0,  0, 0,  0, 100},
        		{-1, 0, -1, 0, -1, -1, -1, -1, -1, -1},
        		{-1, -1, -1, 0, -1, -1, -1, -1, -1, -1},
        		{-1, -1, -1, 0, -1, -1, -1, -1, -1, -1},
        		{-1, -1, 0,  0, -1, -1, -1, -1, 0, -1},
        		{-1, -1, -1, 0,  -1, -1, -1, 0, -1, -1},
        		{-1, 0, -1, 0, -1, -1, -1, -1, -1, 100}
        };

        QLearning q=new QLearning(R, 10, 0.9);
    	long time=q.train();
    	double step=q.test();
    	System.out.println("stati visitati in apprendimento: "+q.getNumberStates());
    	System.out.println("tempo nella fase di apprendimento: "+time/1000);
    	System.out.println("passi necessari per raggiungere l'obiettivo: "+step);
    	System.out.println("numero episodi: "+q.getIterations());
    	System.out.println("discount factor: "+q.getDiscountFactor());
    	
    	
    }
}