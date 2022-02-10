import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.HashSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


class frames extends JFrame implements ActionListener{
    JComboBox cb;
    JFrame frame;
    int tmp = 0;

    JSONArray btc= new JSONArray();
    JSONArray das= new JSONArray();
    JSONArray ltc= new JSONArray();
    JSONArray dog= new JSONArray();
    JSONArray zec= new JSONArray();
    JSONArray eth= new JSONArray();

    frames(JSONArray btcprices, JSONArray dashprices, JSONArray dogeprices, JSONArray ethprices, JSONArray ltcprices, JSONArray zecprices ){
        String currency[] = {"Bitcoin", "Ethereum", "Litcoin", "Zcash", "Dash", "Dogecoin"};
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(150,150);
        this.setTitle("Cryptocurrency Analyser");
        this.setLayout(null);

        cb = new JComboBox(currency);
        cb.setBounds(20, 40, 100, 30);
        cb.addActionListener(this);
        this.add(cb);
        this.setVisible(true);

        btc= btcprices;
        das= dashprices;
        ltc= ltcprices;
        dog= dogeprices;
        zec= zecprices;
        eth= ethprices;

    }

    void graph(int k){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if(tmp != 0)
                    frame.dispose();

                frame = new JFrame("Charts");
                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setBackground(Color.WHITE);
                frame.setVisible(true);

                XYDataset ds = createDataset(k);
                JFreeChart chart = ChartFactory.createXYLineChart("Cryptocurrency Analyser","Days", "Price in $", ds, PlotOrientation.VERTICAL, true, true,false);
                chart.getPlot().setBackgroundPaint(new Color(229, 150, 97, 60));

                ChartPanel cp = new ChartPanel(chart);

                frame.getContentPane().add(cp);
                tmp++;
            }
        });}

    XYDataset createDataset(int i) {

        DefaultXYDataset ds = new DefaultXYDataset();

        if(i ==1){
            double[] x = new double[10];
            for( int v=0; v < 7; v++){
                Object obj = btc.get(v);
                String str = obj.toString();
                x[v] = Double.valueOf(str).doubleValue();
            }
            double[][] data = { {1, 2, 3, 4, 5, 6, 7}, {x[0], x[1], x[2], x[3], x[4], x[5], x[6]} };
            ds.addSeries("Bitcoin", data);
        }

        else if(i==2){
            double[] x = new double[10];
            for( int v=0; v < 7; v++){
                Object obj = eth.get(v);
                String str = obj.toString();
                x[v] = Double.valueOf(str).doubleValue();
            }
            double[][] data = { {1, 2, 3, 4, 5, 6, 7}, {x[0], x[1], x[2], x[3], x[4], x[5], x[6]} };
            ds.addSeries("Ethereum", data);
        }

        else if(i==3){
            double[] x = new double[10];
            for( int v=0; v < 7; v++){
                Object obj = das.get(v);
                String str = obj.toString();
                x[v] = Double.valueOf(str).doubleValue();
            }
            double[][] data = { {1, 2, 3, 4, 5, 6, 7}, {x[0], x[1], x[2], x[3], x[4], x[5], x[6]} };
            ds.addSeries("Dash", data);
        }

        else if(i==4){
            double[] x = new double[10];
            for( int v=0; v < 7; v++){
                Object obj = ltc.get(v);
                String str = obj.toString();
                x[v] = Double.valueOf(str).doubleValue();
            }
            double[][] data = { {1, 2, 3, 4, 5, 6, 7}, {x[0], x[1], x[2], x[3], x[4], x[5], x[6]} };
            ds.addSeries("Litcoin", data);
        }

        else if(i==5){
            double[] x = new double[10];
            for( int v=0; v < 7; v++){
                Object obj = zec.get(v);
                String str = obj.toString();
                x[v] = Double.valueOf(str).doubleValue();
            }
            double[][] data = { {1, 2, 3, 4, 5, 6, 7}, {x[0], x[1], x[2], x[3], x[4], x[5], x[6]} };
            ds.addSeries("Zcash", data);
        }

        else if(i==6){
            double[] x = new double[10];
            for( int v=0; v < 7; v++){
                Object obj = dog.get(v);
                String str = obj.toString();
                x[v] = Double.valueOf(str).doubleValue();
            }
            double[][] data = { {1, 2, 3, 4, 5, 6, 7}, {x[0], x[1], x[2], x[3], x[4], x[5], x[6]} };
            ds.addSeries("Dogecoin", data);
        }
        return ds;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == cb){
            String selection = cb.getSelectedItem().toString();
            if(selection.equals("Bitcoin")){
                graph(1);
            }
            else if(selection.equals("Ethereum")){
                graph(2);

            }
            else if(selection.equals("Dash")){
                graph(3);

            }
            else if(selection.equals("Litcoin")){
                graph(4);

            }
            else if(selection.equals("Zcash")){
                graph(5);

            }
            else if(selection.equals("Dogecoin")){
                graph(6);

            }
        }
    }

}


public class GraphPanel{
    
    static String api_key = "[YOUR-API-KEY]";
    
    
    public static void main(String[] args){
        String inline = "";
        JSONArray btcprices = new JSONArray();
        JSONArray dashprices = new JSONArray();
        JSONArray ltcprices = new JSONArray();
        JSONArray dogeprices = new JSONArray();
        JSONArray zecprices = new JSONArray();
        JSONArray ethprices = new JSONArray();


        try
        {
            URL url = new URL("https://api.nomics.com/v1/currencies/sparkline?key=" + api_key + "&ids=ETH,LTC,BTC,DASH,DOGE,ZEC&start=2021-04-01T00%3A00%3A00Z&end=2021-05-14T00%3A00%3A00Z");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");

            conn.connect();

            int responsecode = conn.getResponseCode();
            System.out.println("Response code is: " + responsecode);


            if(responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " +responsecode);
            else
            {
                Scanner sc = new Scanner(url.openStream());
                while(sc.hasNext())
                {
                    inline += sc.nextLine();
                }
                Object obj = JSONValue.parse(inline);
                JSONArray array = (JSONArray)obj;

                JSONObject btc = (JSONObject)array.get(0);
                btcprices = (JSONArray) btc.get("prices");

                JSONObject dash = (JSONObject)array.get(1);
                dashprices = (JSONArray) dash.get("prices");

                JSONObject doge = (JSONObject)array.get(2);
                dogeprices = (JSONArray) doge.get("prices");

                JSONObject eth = (JSONObject)array.get(3);
                ethprices = (JSONArray) eth.get("prices");

                JSONObject ltc = (JSONObject)array.get(4);
                ltcprices = (JSONArray) eth.get("prices");

                JSONObject zec = (JSONObject)array.get(5);
                zecprices = (JSONArray) eth.get("prices");
                sc.close();

            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        frames window = new frames(btcprices, dashprices, dogeprices, ethprices, ltcprices, zecprices);
    }
}
