import java.io.*;

public class Test {
    public static void write() {
        File csv = new File("/Users/umeng/Documents/bigdata/ml-latest-small/ratings.csv");
        File csv1 = new File("/Users/umeng/Documents/bigdata/ml-latest-small/ratings1.csv");
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            if (csv1.exists()) {
                csv1.delete();
            }
            csv1.createNewFile();

            bw = new BufferedWriter(new FileWriter(csv1));
            br = new BufferedReader(new FileReader(csv));

            String temp = "";
            while ((temp = br.readLine()) != null) {
                bw.write(temp+"\n");
            }
            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }

        BufferedReader br1 = null;

        try {
            br1 = new BufferedReader(new FileReader(csv1));
            String temp = "";
            while ((temp = br1.readLine()) != null) {
                System.out.println(temp+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] arfs){
        write();
    }
}
