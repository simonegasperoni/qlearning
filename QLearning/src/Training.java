public class Training {
    public static void main(String[] args){
        //columns actions
    	//rows states
    	int R1[][] = new int[][]{     //environment topography
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
        //columns actions
      	//rows states
      	int R2[][] = new int[][]{    
      		 //      0    1   2   3   4   5   6   7   8   9   10  11  12  13
                   {-1, -1, -1, -1, -1, -1, -1, -1, -1,  0,  0, -1, -1, -1}, //00
                   {-1, -1,  0,  0,  0,  0, -1, -1, -1, -1, -1, -1, -1, -1}, //01
                   {-1,  0, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1, -1}, //02
      			   {-1,  0, -1, -1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1}, //03
                   {-1,  0, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1,  0, -1}, //04
                   {-1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 100}, //05
      			   {-1, -1,  0, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1}, //06
                   {-1, -1,  0, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1}, //07
                   {-1, -1, -1,  0, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1}, //08
      			   { 0, -1, -1,  0, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1}, //09
                   { 0, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1,  0, -1, -1}, //10
                   {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1,  0, -1}, //11
                   {-1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1,  0, -1, -1}, //12
                   {-1, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, 100}  //13		

        };
        QLearning q=new QLearning(R2, 8, 2, 0.9);
      	long time=q.train(QLearning.mode.WITHOUTSTACK);
      	double step=q.test();
      	System.out.println("stati visitati in apprendimento: "+q.getNumberStates());
      	System.out.println("tempo nella fase di apprendimento: "+time/1000);
      	System.out.println("passi necessari per raggiungere l'obiettivo: "+step);
    }
}