import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GameController {
    private Scanner scanner;
    private boolean running;
    private static Novice player ;

    private JFrame gameGUI;
    private Container gameUI;
    private Bag bag = new Bag();
    Monster monster;

    private JPanel status;
    private JPanel command;
    private JProgressBar monsterhealthpointbar;

    //action listener
    JButton statusbt;


    public GameController(){

        scanner = new Scanner(System.in);
        running = false;

        gameGUI = new JFrame();
        gameGUI.setTitle("My Java Game");
        gameGUI.setSize(600,400);
        gameGUI.setLocationRelativeTo(null);
        gameGUI.setResizable(false);
        gameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameUI = gameGUI.getContentPane();
        gameUI.setLayout(new BorderLayout());
        monster = new Monster("Monster1");





    }

    public void checkAll(){
        //check LV.Up
        System.out.println("Check a;; is used");
        checklevelUp();
        if (player.isDead()){
            JOptionPane gameOver = new JOptionPane();
            JOptionPane.showMessageDialog(gameOver,"GAME OVER! \n You are dead!!");
            System.exit(0);
        }

        System.out.println(player.getHealthPoint());

    }

    public void createCharacter(){
        player = new Novice(JOptionPane.showInputDialog("Enter you character name"));
        checkAll();
    }

    public void showStatus(){
        JOptionPane statusDialog = new JOptionPane();
        String text =   "EXP : "+player.getExp()+"\n"+
                        "LV : "+player.getLevel()+"\n"+
                        "HP : "+player.getHealthPoint()+"/"+player.getMaxHealthPoint()+"\n"+
                        "MP :"+player.getManaPoint()+"/"+player.getMaxManapoint()+"\n"+
                        "Monsterkill : "+ player.getMonsterKill()+ " Kill"+"\n"+
                        "Money :"+ player.getMoney() +"\n"+
                        "Healing Potion : "+ bag.healingPotion.getHaveit()+"\n"+
                        "Mana Potion : "+ bag.manaPotion.getHaveit()+"\n";


        JOptionPane.showMessageDialog(statusDialog,text,"Status",JOptionPane.INFORMATION_MESSAGE);
        checkAll();
    }

    public void checklevelUp(){
        int level = player.getExp()/20;
            player.setLevel(level);
    }


    public void makeGUI(){
        //createCharacter();

        JLabel namelb = new JLabel(player.getName().toUpperCase() + "                Monster kill : " +player.getMonsterKill() + " Kill");

        status = new JPanel();
        status.setLayout(new BoxLayout(status,BoxLayout.Y_AXIS));
        namelb.setFont(new Font("Serif",Font.PLAIN,20));

        //player health progress bar
        JProgressBar playerhealthpointbar = new JProgressBar();
        playerhealthpointbar.setString("HP : "+ player.getHealthPoint()+"/"+ player.getMaxHealthPoint());
        playerhealthpointbar.setForeground(Color.RED);
        playerhealthpointbar.setStringPainted(true);
        playerhealthpointbar.setValue(player.getHealthPoint());

        //mana progress bar
        JProgressBar manapointbar = new JProgressBar();
        manapointbar.setString("MP : "+ player.getManaPoint()+"/"+ player.getMaxManapoint());
        manapointbar.setForeground(Color.BLUE);
        manapointbar.setStringPainted(true);
        manapointbar.setValue(player.getManaPoint());

        //monster health progress bar
        monsterhealthpointbar = new JProgressBar();
        monsterhealthpointbar.setString("HP : "+monster.getHealthPoint()+"/"+ monster.getMaxHealthPoint());
        monsterhealthpointbar.setForeground(Color.GREEN);
        monsterhealthpointbar.setStringPainted(true);
        monsterhealthpointbar.setValue(monster.getHealthPoint());



        //Show status button
        statusbt = new JButton();
        statusbt.setPreferredSize(new Dimension(130,30));
        statusbt.setText("Show Status");

        statusbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Status is pressed");
                showStatus();
            }
        });



        //use red potion button
        JButton useredpotionbt = new JButton();
        useredpotionbt.setPreferredSize(new Dimension(130,30));
        useredpotionbt.setText("Use Red Potion");

        useredpotionbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Use potion is press");
                if(bag.healingPotion.getHaveit()>0 && player.getHealthPoint() != player.getMaxHealthPoint()) {
                    bag.healingPotion.setHaveit(bag.healingPotion.getHaveit() - 1);
                    if(player.getHealthPoint() < player.getMaxHealthPoint() - bag.healingPotion.getHealingPoint())
                        player.setHealthPoint(player.getHealthPoint()+ bag.healingPotion.getHealingPoint());
                    else
                        player.setHealthPoint(player.getMaxHealthPoint());
                }
                makeGUI();
                checkAll();
            }
        });

        //use red potion button
        JButton usebluepotionbt = new JButton();
        usebluepotionbt.setPreferredSize(new Dimension(130,30));
        usebluepotionbt.setText("Use Blue Potion");

        usebluepotionbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Use potion is press");
                if(bag.manaPotion.getHaveit()>0 && player.getManaPoint() != player.getMaxManapoint()) {
                    bag.manaPotion.setHaveit(bag.manaPotion.getHaveit() - 1);
                    if(player.getManaPoint() < player.getMaxManapoint() - bag.manaPotion.getHealingPoint())
                        player.setManaPoint(player.getManaPoint() + bag.manaPotion.getHealingPoint());
                    else
                        player.setManaPoint(player.getMaxManapoint());
                }
                makeGUI();
                checkAll();
            }
        });

        //Attack monster button
        JButton attackmonsterbt = new JButton();
        attackmonsterbt.setPreferredSize(new Dimension(130,30));
        attackmonsterbt.setText("Attack monster");

        attackmonsterbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Attack monster is press");
                attackMonster();
                makeGUI();
            }
        });

        status.add(namelb);
        status.add(playerhealthpointbar);
        status.add(manapointbar);

        command = new JPanel();
        command.setLayout(new FlowLayout());
        command.add(statusbt);
        command.add(useredpotionbt);
        command.add(usebluepotionbt);
        command.add(attackmonsterbt);


        //add component
        gameUI.add(status,BorderLayout.NORTH);
        gameUI.add(command,BorderLayout.CENTER);
        gameUI.add(monsterhealthpointbar,BorderLayout.SOUTH);

        gameGUI.setVisible(true);
    }

    private void attackMonster(){
        monster = new Monster("monsterLV1");
        System.out.println(player.getName());
        while((!player.isDead()) && (!monster.isDead())){
            player.attack(monster);
            checkAll();
            if(!monster.isDead()) {
                player.setManaPoint(player.getManaPoint() - 3);
                monster.attack(player);
                checkAll();
            }
            else {
                JOptionPane msg1 = new JOptionPane();
                JOptionPane.showMessageDialog(msg1,"Monster is dead!");
                player.setMonsterKill(1);
                player.setExp(50);
                player.setMoney(100);
                checkAll();
                break;
            }
            JOptionPane attackAgain = new JOptionPane();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(attackAgain, "Monster is not dead! \n Attack Again", "Attack!", dialogButton);
            checkAll();
            makeGUI();
            if(dialogResult != 0)
                break;
        }
    }
}