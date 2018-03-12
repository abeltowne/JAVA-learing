import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Tupian extends JFrame implements ActionListener{
    //以下四个定义都是对窗体可视化的点的定义
    JLabel jlResult=new JLabel("");
    JLabel jl1=new JLabel("");
    JLabel jl2=new JLabel("");
    JMenuBar jmb=new JMenuBar();
    JMenu jm=new JMenu("选择两张西红柿图片");
    JMenuItem jmi=new JMenuItem("选择图片");
    JButton btn = new JButton("开始比较西红柿的轻熟度");
    //申明静态变量
    static String newMapPath =null;
    static String oldMapPath =null;
    static int temFirstMapRAndG[];
    static int temSectMapRAndG[];

    //图层的左右位置
    JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JFileChooser chooser=new JFileChooser();
    public Tupian() {
        super("浏览图片");
        jmb.add(jm);
        jm.add(jmi);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jlResult);
        jmb.add(btn);
        jlResult.setLocation(50,500);
        jlResult.setForeground(Color.red);
        jlResult.setText("请选择完两张图片以后重新点击按钮");
        btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==btn)
                {//btn被点击
                    jlResult.setLocation(200,400);
                    Color backGCol = getBackground();
                    System.out.println(backGCol);
            if(backGCol==Color.green){
                System.out.println("请选择完两张图片以后重新点击按钮");
            }else {
                Model comPareModel = new Model();
                temFirstMapRAndG = comPareModel.getTomatoRGB(newMapPath);
                temSectMapRAndG = comPareModel.getTomatoRGB(oldMapPath);
                DisPlaywhichIsMoreRipe(temFirstMapRAndG,temSectMapRAndG);
            }
                }
            }
        });
        //这个最为关键 属于监听事件
        jmi.addActionListener(this);
        //下面这个是对应的关闭窗口的参数默认X就关闭窗口
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //这个是文件的位置
        this.add(jmb,BorderLayout.NORTH);
        //这个是文字的位置
        this.add(jp,BorderLayout.WEST);
        //这个是窗口的大小，像素宽高
        this.setSize(600,600);
        //这个是是否显示可见
        this.setVisible(true);
    }
    public  void DisPlaywhichIsMoreRipe(int [] firstRAndG, int [] secRAndG){
        if(firstRAndG[0]>secRAndG[0] && firstRAndG[1]<secRAndG[1]){
            jlResult.setText("Result:   第一张图片更熟");
        }else if ((firstRAndG[0]-secRAndG[0])>(secRAndG[1]-firstRAndG[1])){
            jlResult.setText("Result:   第一张图片更熟");
        }else {
            jlResult.setText("Result:   第二张图片更熟");
        }
    }
    public void actionPerformed(ActionEvent e){
        int i=chooser.showOpenDialog(this);
        System.out.println(i);
        //打开按钮

        if(i==chooser.APPROVE_OPTION){
            oldMapPath = newMapPath;
            newMapPath = chooser.getSelectedFile().getPath();
            Image image=new ImageIcon(newMapPath).getImage();
            image=image.getScaledInstance(300, 300, Image.SCALE_DEFAULT );//调整图像大小400,400
            //第一次加载的时候getIcon获取的默认值为null
            Icon oldIcon =jl1.getIcon();
            System.out.println(jl1.getIcon());
            System.out.println(image);
            //下面这个才是真正的加载图片，下面两行代码的顺序很重要
            jl2.setIcon(oldIcon);
            jl1.setIcon(new ImageIcon(image));
            if (oldIcon==null){}else {btn.setBackground(Color.green);}
            System.out.println(btn.getBackground());
//            jl.setText("这张图片更加熟一点");
            //这里处理监听button的事件
        }
        //取消按钮
        if(i==chooser.CANCEL_OPTION)return;
    }
    public static void main (String[] args) {
        new Tupian();
    }
}