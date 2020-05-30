/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocolatechewsday;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class ChocolateChewsday {

    /**
     * @param args the command line arguments
     */
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        // TODO code application logic here
        
        for (int i=0;i<2;i++) {
            doTestCase();
        }
    }

    private static void doTestCase() {
        int chocolates=sc.nextInt();
        sc.nextLine();
        Score maxScore=new Score(0,0,0);
        ArrayList<Chocolate> chocoList=new ArrayList();
        String name=sc.nextLine();
        for (int i=0;i<chocolates;i++) {
            Chocolate chocolate=readInChocolate();
            String tmp=name;
            name=chocolate.getName();
            chocolate.setName(tmp);
            
            int compare=chocolate.getScore().compareTo(maxScore);
            if (compare>0) {
                maxScore=chocolate.getScore();
                chocoList.clear();
                chocoList.add(chocolate);
            } else if (compare==0) {
                chocoList.add(chocolate);
            }
        }
        for (int i=0;i<chocoList.size();i++) {
            if (i!=0) System.out.print(",");
            System.out.print(chocoList.get(i).getName());
        }
        System.out.println();
    }

    private static Chocolate readInChocolate() {
        Chocolate choco=new Chocolate();
        String line=sc.nextLine();
        String[] tokens=line.split(" ");
        while (tokens[0].equals("J")) { //"123"==>123;
            Score score=new Score(Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3])
            );
            choco.getScore().add(score);
            line=sc.nextLine();
            tokens=line.split(" ");
        }
        choco.setName(line);
        return choco;
    }

    
    
}

class Chocolate {
    private String name=null;
    private Score score=new Score(0,0,0);
    public Chocolate() {
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Score implements Comparable {
    private int p=0;
    private int f=0;
    private int g=0;
    
    public Score(int x1,int x2, int x3) {
        this.p=x1;
        this.f=x2;
        this.g=x3;
    }

    void add(Score score) {
        this.f+=score.f;
        this.g+=score.g;
        this.p+=score.p;
    }

    @Override
    public int compareTo(Object o) {
        Score score=(Score)o;
        int t1=this.p+this.f+this.g;
        int t2=score.p+score.g+score.f;
        if (t1>t2) return 1;
        else if (t1<t2) return -1;
        if (this.g>score.g) return 1;
        else if (this.g<score.g) return -1;
        if (this.f>score.f) return 1;
        else if (this.f<score.f) return -1;
        if (this.p>score.p) return 1;
        else if (this.p<score.p) return -1;
        return 0;
    }
}