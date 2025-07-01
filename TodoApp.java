import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoApp extends JFrame {

    private DefaultListModel<String> todoListModel;
    private JList<String> todoList;
    private JTextField taskInputField;
    private JButton addButton;
    private JButton deleteButton;

    public TodoApp() {
        setTitle("To-Do List App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); 

        todoListModel = new DefaultListModel<>();

        todoList = new JList<>(todoListModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(todoList);
        scrollPane.setPreferredSize(new Dimension(380, 300));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Your Tasks"));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));
 
        taskInputField = new JTextField();
        taskInputField.setPreferredSize(new Dimension(250, 30));
        taskInputField.setText("Enter new task here...");
        taskInputField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (taskInputField.getText().equals("Enter new task here...")) {
                    taskInputField.setText("");
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (taskInputField.getText().isEmpty()) {
                    taskInputField.setText("Enter new task here...");
                }
            }
        });

        addButton = new JButton("Add Task");
        addButton.addActionListener(new AddButtonListener());
        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
 
        deleteButton = new JButton("Delete Selected Task");
        deleteButton.addActionListener(new DeleteButtonListener());

        buttonPanel.add(deleteButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); 

        add(inputPanel, BorderLayout.NORTH);    
        add(scrollPane, BorderLayout.CENTER);  
        add(buttonPanel, BorderLayout.SOUTH);  
        setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String task = taskInputField.getText().trim(); 
            if (!task.isEmpty() && !task.equals("Enter new task here...")) {
                todoListModel.addElement(task);
                taskInputField.setText("");
            } else {
                JOptionPane.showMessageDialog(TodoApp.this,
                        "Please enter a task!",
                        "Empty Task",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int selectedIndex = todoList.getSelectedIndex();

            if (selectedIndex != -1) { 
                todoListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(TodoApp.this,
                        "Please select a task to delete!",
                        "No Task Selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TodoApp();
        });
    }
}
