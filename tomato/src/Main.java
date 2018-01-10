import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Main {

    public static void getData(String path){
        try{
            BufferedImage bimg = ImageIO.read(new File(path));
            int [][] data = new int[bimg.getWidth()][bimg.getHeight()];
            //方式一：通过getRGB()方式获得像素矩阵
            //此方式为沿Height方向扫描
            for(int i=0;i<bimg.getWidth();i++){
                for(int j=0;j<bimg.getHeight();j++){
                    data[i][j]=bimg.getRGB(i,j);
                    //输出一列数据比对
                    if(i==0)
                        System.out.printf("%x\t",data[i][j]);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void getLenth(String path){
        try{
            BufferedImage img = ImageIO.read(new File(path));
            int imgWith = img.getWidth();
            int imgHeight = img.getHeight();
            System.out.println(imgWith);
            System.out.println(imgHeight);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void getTomatoRGB(String path){
        try{
            int rTotal=0,gTotal=0,bTotal=0;
            BufferedImage img = ImageIO.read(new File(path));
            for(int i=0;i<img.getWidth();i++){
                for(int j=0;j<img.getHeight();j++){
                    int rgb = img.getRGB(i,j);
                    int r = (rgb & 0xff0000) >> 16;
                    int g = (rgb & 0xff00) >> 8;
                    int b = (rgb & 0xff);
                    rTotal = rTotal + r;
                    gTotal = gTotal + g;
                    bTotal = bTotal + b;
                }
            }
            int Width = img.getWidth();
            int Height = img.getHeight();
            System.out.println(rTotal/(Width*Height));
            System.out.println(gTotal/(Width*Height));
            System.out.println(bTotal/(Width*Height));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String [] args){
        System.out.println("没有熟的西红柿的像素：");
        getData("G:\\greeTomato.jpg");
        System.out.println("已经熟的西红柿的像素：");
        getData("G:\\redTomato.jpg");
        System.out.println("获取像素的宽高值：");
        getLenth("G:\\greeTomato.jpg");
        getLenth("G:\\redTomato.jpg");
        System.out.println("获取图片的平均的RGB值：");
        getTomatoRGB("G:\\greeTomato.jpg");
        getTomatoRGB("G:\\redTomato.jpg");
        getTomatoRGB("G:\\red1.jpg");
        getTomatoRGB("G:\\red2.jpg");
        getTomatoRGB("G:\\red3.jpg");
        System.out.println("初步断定西红柿的R的值大于150的时候，红色程度较为高，定义为成熟");

    }
}