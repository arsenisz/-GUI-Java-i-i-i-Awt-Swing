import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI додаток для обчислення відстані між двома точками на поверхні Землі
 * за формулою гаверсинуса (Haversine formula)
 *
 * Автор: Студент групи ЦК-31 Оврас Арсен
 * Завдання 1 - Розробка GUI в Java
 */
public class DistanceCalculator extends JFrame implements ActionListener {

    // Графічні компоненти
    private JTextField lat1Field, lon1Field, lat2Field, lon2Field, radiusField, resultField;
    private JButton solveButton, clearButton;
    private JLabel lat1Label, lon1Label, lat2Label, lon2Label, radiusLabel, resultLabel;

    // Константа - радіус Землі в метрах
    private static final double EARTH_RADIUS = 6371e3;

    /**
     * Конструктор - налаштування GUI компонентів
     */
    public DistanceCalculator() {
        // Налаштування вікна
        setTitle("Обчислення відстані між точками на Землі");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Центрування вікна

        // Створення панелі з GridBagLayout для гнучкого розташування
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Заголовок
        JLabel titleLabel = new JLabel("Калькулятор відстані (формула гаверсинуса)");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Точка 1 - Широта
        lat1Label = new JLabel("Широта точки 1 (lat1, °):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(lat1Label, gbc);

        lat1Field = new JTextField(15);
        lat1Field.setText("50.4501"); // Київ - приклад
        gbc.gridx = 1;
        mainPanel.add(lat1Field, gbc);

        // Точка 1 - Довгота
        lon1Label = new JLabel("Довгота точки 1 (lon1, °):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(lon1Label, gbc);

        lon1Field = new JTextField(15);
        lon1Field.setText("30.5234"); // Київ - приклад
        gbc.gridx = 1;
        mainPanel.add(lon1Field, gbc);

        // Точка 2 - Широта
        lat2Label = new JLabel("Широта точки 2 (lat2, °):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(lat2Label, gbc);

        lat2Field = new JTextField(15);
        lat2Field.setText("48.8566"); // Париж - приклад
        gbc.gridx = 1;
        mainPanel.add(lat2Field, gbc);

        // Точка 2 - Довгота
        lon2Label = new JLabel("Довгота точки 2 (lon2, °):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(lon2Label, gbc);

        lon2Field = new JTextField(15);
        lon2Field.setText("2.3522"); // Париж - приклад
        gbc.gridx = 1;
        mainPanel.add(lon2Field, gbc);

        // Радіус Землі
        radiusLabel = new JLabel("Радіус Землі (R, м):");
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(radiusLabel, gbc);

        radiusField = new JTextField(15);
        radiusField.setText(String.valueOf(EARTH_RADIUS));
        gbc.gridx = 1;
        mainPanel.add(radiusField, gbc);

        // Результат
        resultLabel = new JLabel("Відстань (D, м):");
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(resultLabel, gbc);

        resultField = new JTextField(15);
        resultField.setEditable(false);
        resultField.setBackground(Color.LIGHT_GRAY);
        gbc.gridx = 1;
        mainPanel.add(resultField, gbc);

        // Панель для кнопок
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        solveButton = new JButton("Solve");
        solveButton.addActionListener(this);
        solveButton.setBackground(new Color(76, 175, 80));
        solveButton.setForeground(Color.WHITE);
        solveButton.setFocusPainted(false);
        buttonPanel.add(solveButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBackground(new Color(244, 67, 54));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        // Додавання панелі до вікна
        add(mainPanel);

        // Відображення вікна
        setVisible(true);
    }

    /**
     * Обробка подій від кнопок
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solveButton) {
            calculateDistance();
        } else if (e.getSource() == clearButton) {
            clearFields();
        }
    }

    /**
     * Обчислення відстані за формулою гаверсинуса
     */
    private void calculateDistance() {
        try {
            // Зчитування даних з текстових полів
            double lat1 = Double.parseDouble(lat1Field.getText());
            double lon1 = Double.parseDouble(lon1Field.getText());
            double lat2 = Double.parseDouble(lat2Field.getText());
            double lon2 = Double.parseDouble(lon2Field.getText());
            double R = Double.parseDouble(radiusField.getText());

            // Конвертація градусів у радіани
            double φ1 = lat1 * Math.PI / 180.0;
            double φ2 = lat2 * Math.PI / 180.0;
            double Δφ = (lat2 - lat1) * Math.PI / 180.0;
            double Δλ = (lon2 - lon1) * Math.PI / 180.0;

            // Формула гаверсинуса
            double a = Math.sin(Δφ / 2.0) * Math.sin(Δφ / 2.0) +
                    Math.cos(φ1) * Math.cos(φ2) *
                            Math.sin(Δλ / 2.0) * Math.sin(Δλ / 2.0);

            double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));

            double D = R * c;

            // Виведення результату
            resultField.setText(String.format("%.2f", D));

            // Додаткова інформація в кілометрах
            JOptionPane.showMessageDialog(this,
                    String.format("Відстань: %.2f м (%.2f км)", D, D / 1000.0),
                    "Результат обчислення",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Помилка! Перевірте правильність введених даних.\n" +
                            "Координати повинні бути числами.",
                    "Помилка введення",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Виникла помилка при обчисленні: " + ex.getMessage(),
                    "Помилка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Очищення всіх полів
     */
    private void clearFields() {
        lat1Field.setText("");
        lon1Field.setText("");
        lat2Field.setText("");
        lon2Field.setText("");
        radiusField.setText(String.valueOf(EARTH_RADIUS));
        resultField.setText("");
    }

    /**
     * Головний метод - точка входу в програму
     */
    public static void main(String[] args) {
        // Використання системного Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Запуск GUI в окремому потоці (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            new DistanceCalculator();
        });
    }
}