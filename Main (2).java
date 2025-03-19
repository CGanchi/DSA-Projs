import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("topo.txt"));
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] image = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                image[i][j] = in.nextInt();
            }
        }

        int sr = in.nextInt();
        int sc = in.nextInt();
        int newColor = in.nextInt();
        for(int i = 0; i < image.length; i++){
            for(int j = 0; j < image[i].length; j++)
                System.out.print(image[i][j] + " ");
            System.out.println();
        }
        System.out.println("Color: " + newColor + " Row: " + sr + " Col: " + sc );
        int[][] ans = floodFill(image, sr, sc, newColor);
        for(int i = 0; i < ans.length; i++){
            for(int j = 0; j < ans[i].length; j++)
                System.out.print(ans[i][j] + " ");
            System.out.println();
        }


    }
    public static int[][] floodFill(int[][] boxes, int row, int col, int newColor){
       return floodFillHelper(boxes, row, col, newColor, boxes[row][col]);
    }
    public static int[][] floodFillHelper(int[][] boxes, int row, int col, int newColor, int ogColor){
        boxes[row][col] = newColor;
        if(row < boxes.length-1 && boxes[row+1][col] == ogColor){
            floodFillHelper(boxes, row+1, col, newColor, ogColor);
        }
        if(col != (boxes[0]).length-1 && boxes[row][col+1] == ogColor){
            floodFillHelper(boxes, row, col+1, newColor, ogColor);
        }
        if(row > 0 && boxes[row-1][col] == ogColor){
           floodFillHelper(boxes, row-1, col, newColor, ogColor);
        }
        if(col > 0 && boxes[row][col-1] == ogColor){
            floodFillHelper(boxes, row, col-1, newColor, ogColor);
        }
        return boxes;

    }
}