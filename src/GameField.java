import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    //Создание игровых компонентов
    private final int SIZE = 320; //Размер игрового поля
    private final int DOT_SIZE = 16; //Размер ячейки
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    //Храним в переменных позицию яблок
    private int appleX;
    private int appleY;
    // Два масива для того чтобы хранить положение змейки
    private int[] x = new int[ALL_DOTS]; //ALL_DOTS В теории змейка может занимать все игровое поле
    private int[] y = new int[ALL_DOTS];
    //Размер змейки в данный момент времени:
    private int dots;
    //Стандыртный таймер Swing:
    private Timer timer;
    // Пять булеан полей, которые отвечают за текущее направление змейки:
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    // Значение определяющее в игре мы или нет
    private boolean inGame = true;

    //Создание конструктора
    public GameField(){
        setBackground(Color.black);
        //Вызов метода который водгружает картинки
        loadImages();
        initGame();
    }
    //Метод инициализации игры:
    public void initGame(){
        //установка начального количествка точек (3)
        dots = 3;
        for (int i = 0; i < dots; i++) {
            // Начальная x позиция
            x[i] = 48 - i * DOT_SIZE;
            // Начальная Y позиция
            y[i] = 48;
        }
        // Создание таймера
        // this необходима для того чтобы при
        // каждом вызове таймера, использовался этот, в классе GameField
        // для этого также необходимо создать специальный метод и имплимитировать специальный интерфейс
        timer = new Timer(250,this);
        // Запуск таймера
        timer.start();
        // Ввызов метода для создания яблока
        createApple();
    }

    public void createApple() {
        // Создание рандомной позиции
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }

    //Метод ддля загрузки картинок
    public void loadImages() {
        //Для того чтобы загрузить картинки с изображением dot и apple:
        ImageIcon iia = new ImageIcon("apple.png");
        // Инициализация изображения
        apple = iia.getImage();

        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();

    }

    // Не могу понять что это и почему, но оно отображает змею и яблоки
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame);
        g.drawImage(apple, appleX, appleY, this);
        for (int i = 0; i <dots ; i ++) {
            g.drawImage(dot, x[i], y[i], this);
        }
    }

    public void move() {
        //Все точки (не головы) перемещаются на предыдущие позиции
        for (int i = dots; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        // Для головы перемещение в ту сторону которая true (boolean):
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] += DOT_SIZE;
        }
        if (down) {
            y[0] -=  DOT_SIZE;
        }
    }
    // Если координаты дота равны координатам яблока, то срабатывает метод createApple
    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            createApple();
        }

    }

    public void checkCollisions() {
        // Проверка столкновения самим с собой
        for (int i = dots; i > 0; i--){
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }
        // Проверка столкновения с бордюрами
        if (x[0] > SIZE) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (y[0] > SIZE) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
    }

    // Метод вызываемый таймером каждый раз когда он тикает (250)
    // Его мы и имплимитировали
    @Override
    public void actionPerformed(ActionEvent e) {
        // Если мы в игре то...
        if (inGame) {
            // Если мы в игре змейка должна двигаться. необходимо создать метод move
            checkApple();
            // Метод который проверяет столкновение с бордюрами и с самим собой
            checkCollisions();
            move();

        }
        // Метод вызывает pain companent для перерисовки (стандартная функция винды)
        repaint();
    }
}
