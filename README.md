# -GUI-Java-i-i-i-Awt-Swing
Розроблення GUI в Java з використанням графiчних бiблiотек: Awt, Swing
## 1. Призначення JFC фреймворку і його архітектура

**JFC (Java Foundation Classes)** - це набір бібліотек для створення графічних інтерфейсів користувача (GUI) в Java.

**Архітектура JFC включає:**
- **AWT (Abstract Window Toolkit)** - базова бібліотека графічних компонентів
- **Swing** - розширена бібліотека легких компонентів
- **Java 2D API** - для роботи з графікою та зображеннями
- **Accessibility API** - для підтримки користувачів з обмеженими можливостями
- **Drag and Drop** - підтримка перетягування об'єктів

---

## 2. Переваги Swing у порівнянні з AWT

**Переваги Swing:**
- **Платформонезалежність** - однаковий вигляд на всіх операційних системах
- **Легкі компоненти** - написані повністю на Java, не залежать від нативних компонентів ОС
- **Більше компонентів** - 18 пакетів з 737 класами (проти 12 пакетів з 370 класами в AWT)
- **Pluggable Look and Feel** - можливість змінювати зовнішній вигляд інтерфейсу
- **Розширена функціональність** - таблиці (JTable), дерева (JTree), вкладки (JTabbedPane)
- **Краща підтримка подій** - більш гнучка модель обробки подій

---

## 3. Приклад структури GUI програми на Java

```java
import javax.swing.*;
import java.awt.event.*;

public class MyGUIProgram extends JFrame implements ActionListener {
    // Приватні змінні - GUI компоненти
    private JButton button;
    private JTextField textField;
    private JLabel label;
    
    // Конструктор - налаштування GUI компонентів та обробників подій
    public MyGUIProgram() {
        // Налаштування вікна
        setTitle("My Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Створення компонентів
        label = new JLabel("Enter text:");
        textField = new JTextField(20);
        button = new JButton("Click Me");
        
        // Додавання обробників подій
        button.addActionListener(this);
        
        // Додавання компонентів до вікна
        add(label);
        add(textField);
        add(button);
        
        // Відображення вікна
        setVisible(true);
    }
    
    // Обробка подій
    public void actionPerformed(ActionEvent e) {
        // Код обробки події
    }
    
    // Головний метод - точка входу
    public static void main(String[] args) {
        new MyGUIProgram();
    }
}
```

**Пояснення елементів:**
- `extends JFrame` - наслідування від класу вікна
- `implements ActionListener` - реалізація інтерфейсу обробки подій
- Приватні змінні - зберігають посилання на GUI компоненти
- Конструктор - ініціалізує та налаштовує інтерфейс
- `actionPerformed()` - метод обробки подій від кнопки
- `main()` - запускає програму

---

## 4. Призначення Containers в Awt/Swing

**Container** - це компонент, який може містити інші компоненти (widgets).

**Призначення:**
- Організація та групування компонентів
- Управління розташуванням компонентів через Layout Managers
- Створення ієрархічної структури інтерфейсу

**Що використовується як Containers:**
- **JFrame** - головне вікно додатку
- **JPanel** - панель для групування компонентів
- **JDialog** - діалогове вікно
- **JApplet** - аплет (застарілий)
- **JWindow** - вікно без рамки
- **JScrollPane** - панель з прокруткою

Контейнери можуть містити інші контейнери (вкладені контейнери).

---

## 5. Основні методи класу Frame для розроблення GUI

```java
// Встановлення заголовку вікна
setTitle(String title)

// Встановлення розміру вікна
setSize(int width, int height)

// Додавання компонента до вікна
add(Component c)

// Встановлення менеджера розташування
setLayout(LayoutManager m)

// Відображення/приховування вікна
setVisible(boolean b)

// Поведінка при закритті вікна
setDefaultCloseOperation(int operation)
// EXIT_ON_CLOSE - завершення програми
// HIDE_ON_CLOSE - приховування вікна
// DISPOSE_ON_CLOSE - знищення вікна

// Центрування вікна на екрані
setLocationRelativeTo(null)

// Заборона зміни розміру
setResizable(boolean resizable)

// Встановлення іконки
setIconImage(Image image)
```

**Приклад використання:**
```java
JFrame frame = new JFrame();
frame.setTitle("My Application");
frame.setSize(500, 400);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLocationRelativeTo(null);
frame.setVisible(true);
```

---

## 6. Призначення об'єкта Panel

**JPanel** - це контейнер для групування та організації компонентів.

**Призначення:**
- Логічне групування пов'язаних компонентів
- Створення складних багаторівневих інтерфейсів
- Застосування різних Layout Managers до різних частин інтерфейсу
- Полегшення управління та обслуговування коду

**Як додати компоненти до Panel:**

```java
// Створення панелі
JPanel panel = new JPanel();

// Встановлення Layout Manager (опціонально)
panel.setLayout(new FlowLayout());

// Додавання компонентів
panel.add(new JLabel("Name:"));
panel.add(new JTextField(20));
panel.add(new JButton("Submit"));

// Додавання панелі до вікна
frame.add(panel);
```

**Приклад з вкладеними панелями:**
```java
JPanel mainPanel = new JPanel(new BorderLayout());
JPanel topPanel = new JPanel();
JPanel bottomPanel = new JPanel();

topPanel.add(new JLabel("Top Section"));
bottomPanel.add(new JButton("OK"));

mainPanel.add(topPanel, BorderLayout.NORTH);
mainPanel.add(bottomPanel, BorderLayout.SOUTH);
```

---

## 7. Механізм обробки подій в Awt/Swing

**Event (подія)** - це об'єкт, що описує зміну стану джерела (наприклад, клік по кнопці).

**Компоненти моделі обробки подій:**

1. **Event Source (Джерело події)** - компонент, на якому відбувається подія (Button, TextField)
2. **Event Object (Об'єкт події)** - об'єкт, що містить інформацію про подію (ActionEvent, MouseEvent)
3. **Event Listener (Слухач події)** - об'єкт, що обробляє подію

**Як працює механізм:**

1. **Реєстрація Listener:**
   ```java
   button.addActionListener(this);
   ```

2. **Виникнення події:** Користувач натискає кнопку

3. **Створення Event Object:** Source створює об'єкт події (ActionEvent)

4. **Виклик Listener:** Source викликає метод listener'а з об'єктом події

5. **Обробка події:** Listener виконує необхідні дії

**Переваги моделі:**
- Логіка інтерфейсу відокремлена від логіки обробки подій
- Ефективність - сповіщення отримують тільки зареєстровані listener'и
- Гнучкість - можна додавати/видаляти listener'и динамічно

**Приклад:**
```java
public class MyClass implements ActionListener {
    private JButton button;
    
    public MyClass() {
        button = new JButton("Click");
        button.addActionListener(this);  // Реєстрація
    }
    
    public void actionPerformed(ActionEvent e) {
        // Обробка події
        if (e.getSource() == button) {
            System.out.println("Button clicked!");
        }
    }
}
```

---

## 8. Призначення інтерфейсів XxxListener

**XxxListener** - це інтерфейси, що визначають методи для обробки різних типів подій.

**Основні типи Listener інтерфейсів:**

### ActionListener
Обробка дій користувача (кліки по кнопках, вибір пунктів меню)
```java
public interface ActionListener {
    void actionPerformed(ActionEvent e);
}

// Використання:
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button pressed!");
    }
});
```

### MouseListener
Обробка подій від миші
```java
public interface MouseListener {
    void mouseClicked(MouseEvent e);
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
    void mouseEntered(MouseEvent e);
    void mouseExited(MouseEvent e);
}

// Використання:
component.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at: " + e.getX() + ", " + e.getY());
    }
});
```

### KeyListener
Обробка подій від клавіатури
```java
public interface KeyListener {
    void keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);
    void keyTyped(KeyEvent e);
}

// Використання:
textField.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Enter pressed!");
        }
    }
});
```

### WindowListener
Обробка подій від вікна
```java
public interface WindowListener {
    void windowOpened(WindowEvent e);
    void windowClosing(WindowEvent e);
    void windowClosed(WindowEvent e);
    void windowIconified(WindowEvent e);
    void windowDeiconified(WindowEvent e);
    void windowActivated(WindowEvent e);
    void windowDeactivated(WindowEvent e);
}

// Використання:
frame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
});
```

### ItemListener
Обробка подій від CheckBox, RadioButton, List
```java
public interface ItemListener {
    void itemStateChanged(ItemEvent e);
}

// Використання:
checkBox.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println("Checked!");
        }
    }
});
```

### MouseMotionListener
Обробка руху миші
```java
public interface MouseMotionListener {
    void mouseDragged(MouseEvent e);
    void mouseMoved(MouseEvent e);
}
```

**Загальна структура використання:**
```java
public class MyApp extends JFrame implements ActionListener {
    private JButton button;
    
    public MyApp() {
        button = new JButton("Click");
        button.addActionListener(this);  // this реалізує ActionListener
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Обробка події
    }
}
```
