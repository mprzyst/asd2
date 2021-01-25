import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;



public class Main {

    static int counter1 = 0, counter2 =0;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new File("C:/Users/mgdle/Documents/in.txt"));
        PrintWriter out = new PrintWriter(new File("C:/Users/mgdle/Documents/out.txt"));
        int i=0, n, temp;
        n= in.nextInt();
        int l=0, r=n-1;
        int [] tab1 = new int[n], tab2 = new int[n], index = new int[n], result = new int[3];

        while(in.hasNextInt()){
            tab1[i] = in.nextInt();
            tab2[i] = tab1[i];
            i++;
        }

        for(i = 0; i<n ; i++){
            index[i] = i;
        }

        if( n % 2 == 0){
            tab1 = quicksort(tab1,index,l,r);
            result = obliczOdleglosciParzysteN(tab1, n);
            temp = result[1];
            temp = index[temp];


            out.println(++temp);
            out.println(result[0]);
            out.println();

            System.out.println("Stacja serwisowa powinna być w punkcie "+ temp);
            System.out.println("odleglosc: "+result[0]);
            System.out.println("Liczba operacji elementarnych - sposób 1: "+counter1);
            System.out.println();


        }
        else{
            tab1 = quicksort(tab1,index,l,r);
            temp = n/2;

            for(int j = 0; j<n; j++) {
                result[0] = result[0] + Math.abs(tab1[j] - tab1[temp]);
            }
            temp = index[temp] + 1;


            out.println(temp);
            out.println(result[0]);
            out.println();

            System.out.println("Stacja serwisowa powinna być w punkcie "+ temp);
            System.out.println("odleglosc: "+result[0]);
            System.out.println("Liczba operacji elementarnych - sposób 1: "+counter1);
            System.out.println();

        }

        result = porownywanie(tab2,n);
        result[1] +=1;
        out.println(result[1]);
        out.println(result[0]);
        out.close();
        System.out.println("Stacja serwisowa powinna być w punkcie "+ result[1]);
        System.out.println("odleglosc: "+result[0]);
        System.out.println("Liczba operacji elementarnych - sposób 2: "+counter2);


    }

    public static int[] quicksort(int []tab, int []index, int l, int r){
        counter1++;
        if(l<r) {
            int p = partition(tab, index, l, r);
            if (p-1>l) quicksort(tab,index, l,p-1);      //dzieli lewą część
            if (r> p+1) quicksort(tab,index, p+1,r);     //dzieli prawą część
        }
        return tab;

    }

    public static int partition(int []tab, int []index, int l, int r) {
        int x = l + ((r-l)/2);
        int divide = tab[x];
        int temp, j = 0;

        temp = tab[x];
        tab[x] = tab[r];
        tab[r] = temp;

        temp = index[x];
        index[x] = index[r];
        index[r] = temp;

        for(int i = 0; i < tab.length-1; i++){
            counter1++;

            if(tab[i] < divide){
                temp = tab[i];
                tab[i] = tab[j];
                tab[j] = temp;

                temp = index[i];
                index[i] = index[j];
                index[j] = temp;

                j++;
            }
        }
        temp = tab[r];
        tab[r] = tab[j];
        tab[j] = temp;

        temp = index[r];
        index[r] = index[j];
        index[j] = temp;

        return j;

    }


    public static int[] porownywanie(int []tab, int n){        //zwykle porownywanie
        int k = 0;
        tab = obliczOdleglosci(tab, n);

        int []result= {0,0,0};
        result[0] = tab[0];

        for(int i = 1; i<n ; i++){
            counter2++;
            if(result[0] > tab[i]){
                result[0] = tab[i];
                result[1] = i;
            }
        }
        result[2] = counter2;
        return result;

    }

    public static int[] obliczOdleglosci(int []tab, int n){
        int []odleglosci = new int[n];

        for(int i = 0; i<n; i++) {
            odleglosci[i] = 0;
            counter2++;
        }

        for(int k = 0; k<n ; k++){
            counter2++;
            for(int j = 0; j<n; j++){
                counter2++;
                odleglosci[k] = odleglosci[k] + Math.abs(tab[j] - tab[k]);
            }
        }
        return odleglosci;
    }

    public static int[] obliczOdleglosciParzysteN(int []tab, int n){
        int []odleglosci = {0,0};
        int temp = n/2;

        for(int i = 0; i<2 ; i++){
            counter1++;
            for(int j = 0; j<n; j++){
                counter1++;
                odleglosci[i] = odleglosci[i] + Math.abs(tab[j] - tab[temp]);
            }
            temp++;
        }
        temp = n/2;
        counter1++;
        if(odleglosci[0] < odleglosci[1]){
            return new int[]{odleglosci[0], temp};
        }
        else{
            return new int[]{odleglosci[1], ++temp};
        }
    }
}
