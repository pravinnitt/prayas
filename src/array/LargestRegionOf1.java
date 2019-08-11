package array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * find Unit Area of largest region of 1's
 *
 *  1 1 0
 *  0 1 0
 *  1 0 1
 *  0 0 1
 */
public class LargestRegionOf1 {


    public static int[][] input ={{0,1,0},{0,0,1},{1,0,1},{1,0,1},{1,0,0},{1,0,1},{1,1,0}};
    public static void main(String[] args){

        LargestRegionOf1 lregion = new LargestRegionOf1();

        UnitCell[][] store = lregion.transformMatrixToCell(input);
        lregion.printInput(store);
        List<UnitCell> largestRange = lregion.findMaxRegion(store);
        System.out.println(" Maximum region length  = " + largestRange.size());
        lregion.printLargestRange(largestRange,input.length,input[0].length);
    }

    public void printLargestRange(List<UnitCell> largestRange,int rowCount,int columnCount)
    {
       int[][] display = new int[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                display[i][j] =0;
            }
        }
        for(UnitCell cell: largestRange){
            display[cell.getXcord()][cell.getYcord()] =1;
        }
        printArray(display);
    }
    public List<UnitCell> findMaxRegion(UnitCell[][] param) {
        int maxLength = 0;
        List<UnitCell> maxlist = new ArrayList<>();
        int rowSize = param.length;
        int columnSize = param[0].length;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                int curLength = 0;
                List<UnitCell> curlist = new ArrayList<>();
                        Queue<UnitCell> list = new LinkedList<>();
                if(param[i][j].getValue()==1 && !param[i][j].isVisited) {
                    list.add(param[i][j]);
                }
                while (!list.isEmpty())
                {
                    UnitCell cur = list.poll();
                    if(!cur.isVisited) {
                        List<UnitCell> neighbours = findPopulatedNeighbour(cur, param);
                        for(UnitCell nbr:neighbours)
                        {
                            if(!nbr.isVisited){
                                list.add(nbr);
                            }
                        }
                        curLength+=1;
                        cur.setVisited(true);
                        curlist.add(cur);
                    }
                }
                if(curLength>maxLength) {
                    maxLength=curLength;
                    maxlist = curlist;
                }
            }
        }

        return maxlist;
    }

    /**
     *
     *          (i-1,j-1)      (i-1,j) (i-1,j+1)
     *          (i,j-1)        (i,j)   (i,j+1)
     *          (i+1,j-1)      (i+1,j) (i+1,j+1)
     *
     * @param cell
     */
    private List<UnitCell> findPopulatedNeighbour(UnitCell cell,UnitCell[][] complete)
    {
        List<UnitCell> neighbours = new ArrayList<>();
        int rowsize = complete.length;
        int colsize = complete[0].length;

        int xcord = cell.getXcord();
        int ycord = cell.getYcord();
        if(xcord !=0){ //previous row can be considerd
            UnitCell above = complete[xcord-1][ycord];
            if(above.getValue()==1) {
                neighbours.add(above);
            }
            if(ycord!=0) {
                UnitCell aboveLeft = complete[xcord-1][ycord-1];
                if(aboveLeft.getValue()==1) {
                    neighbours.add(aboveLeft);
                }
            }
            if(ycord!=colsize-1) {
                UnitCell aboveRight = complete[xcord-1][ycord+1];
                if(aboveRight.getValue()==1) {
                    neighbours.add(aboveRight);
                }
            }
        }
        if(xcord < rowsize-1) {// Next row can be considerd
            UnitCell below = complete[xcord+1][ycord];
            if(below.getValue()==1) {
                neighbours.add(below);
            }
            if(ycord!=0) {
                UnitCell belowLeft = complete[xcord+1][ycord-1];
                if(belowLeft.getValue()==1) {
                    neighbours.add(belowLeft);
                }
            }
            if(ycord!=colsize-1) {
                UnitCell belowRight = complete[xcord+1][ycord+1];
                if(belowRight.getValue()==1) {
                    neighbours.add(belowRight);
                }
            }

        }
        if(ycord!=0) {
            UnitCell Left = complete[xcord][ycord-1];
            if(Left.getValue()==1) {
                neighbours.add(Left);
            }
        }
        if(ycord!=colsize-1) {
            UnitCell Right = complete[xcord][ycord+1];
            if(Right.getValue()==1) {
                neighbours.add(Right);
            }
        }
        return neighbours;
    }


    public UnitCell[][] transformMatrixToCell(int[][] param) {
        int rowSize = param.length;
        int columnSize = param[0].length;
        UnitCell[][] temp = new UnitCell[rowSize][columnSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                UnitCell cell = new UnitCell(i,j,param[i][j]);
                temp[i][j] =cell;
            }
            System.out.println();
        }

        return  temp;
    }
    public void printInput(UnitCell[][] param) {
        int rowSize = param.length;
        int columnSize = param[0].length;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j <columnSize ; j++) {
                System.out.print(" " +param[i][j].getValue());
            }
            System.out.println();
        }
    }
    public void printArray(int[][] param) {
        int rowSize = param.length;
        int columnSize = param[0].length;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j <columnSize ; j++) {
                System.out.print(" " +param[i][j]);
            }
            System.out.println();
        }
    }
}

 class UnitCell {
     public int getXcord() {
         return xcord;
     }

     public void setXcord(int xcord) {
         this.xcord = xcord;
     }

     public int getYcord() {
         return Ycord;
     }

     public void setYcord(int ycord) {
         Ycord = ycord;
     }

     public int getValue() {
         return value;
     }

     public void setValue(int value) {
         this.value = value;
     }

     public boolean isVisited() {
         return isVisited;
     }

     public void setVisited(boolean visited) {
         isVisited = visited;
     }

     int xcord, Ycord,value;
    boolean isVisited;
     UnitCell parent;

     UnitCell(int x, int y, int val) {
         this.xcord =x;
         this.Ycord =y;
         this.value =val;
         this.isVisited =false;
         this.parent=null;
     }
}
