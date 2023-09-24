package studentManagement;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import inJDBCutil.JDBCUtil;

public class StudentManagementSystem extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField name;
    private JTextField rollNo;
    private JTextField totalMarks;
    private JTextField grade;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentManagementSystem frame = new StudentManagementSystem();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public StudentManagementSystem() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 602, 524);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblName = new JLabel("NAME");
        lblName.setBounds(33, 29, 73, 21);
        contentPane.add(lblName);

        JLabel lblRollNo = new JLabel("ROLL NO");
        lblRollNo.setBounds(33, 82, 73, 21);
        contentPane.add(lblRollNo);

        JLabel lblTotalMarks = new JLabel("TOTAL MARKS");
        lblTotalMarks.setBounds(33, 145, 73, 21);
        contentPane.add(lblTotalMarks);

        JLabel lblGrade = new JLabel("GRADE");
        lblGrade.setBounds(33, 205, 73, 21);
        contentPane.add(lblGrade);

        name = new JTextField();
        name.setBounds(147, 30, 164, 19);
        contentPane.add(name);

        rollNo = new JTextField();
        rollNo.setBounds(147, 83, 164, 19);
        contentPane.add(rollNo);

        totalMarks = new JTextField();
        totalMarks.setBounds(147, 146, 164, 19);
        contentPane.add(totalMarks);

        grade = new JTextField();
        grade.setBounds(147, 206, 164, 19);
        contentPane.add(grade);

        JButton submit = new JButton("SUBMIT");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sname = name.getText();
                String srollNo = rollNo.getText();
                String stotalMarks = totalMarks.getText();
                String sgrade = grade.getText();

                if (sname.isEmpty() || srollNo.isEmpty() || stotalMarks.isEmpty() || sgrade.isEmpty()) {
                    // Show an error message if any of the fields is empty
                    JOptionPane.showMessageDialog(null, "All fields are required.");
                } else {
                    // All fields are filled, proceed with database insertion
                    insertStudentRecord(sname, srollNo, stotalMarks, sgrade);
                }
            }
        });
        submit.setBounds(33, 325, 85, 21);
        contentPane.add(submit);

        JButton clear = new JButton("CLEAR");
        clear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent args0) {
        		name.setText("");
               rollNo.setText("");
                totalMarks.setText("");
                grade.setText("");

        		
        	}
        });
        clear.setBounds(318, 325, 85, 21);
        contentPane.add(clear);
    }

    // Method to insert student record into the database
    private void insertStudentRecord(String sname, String srollNo, String stotalMarks, String sgrade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtil.getJdbcConnection();
            if (connection != null) {
                String mysqlQuery = "INSERT INTO student(name, rollNo, totalmarks, grade) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(mysqlQuery);
                preparedStatement.setString(1, sname);
                preparedStatement.setString(2, srollNo);
                preparedStatement.setString(3, stotalMarks);
                preparedStatement.setString(4, sgrade);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Row inserted successfully.");
                } else {
                    System.out.println("Row not inserted.");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.closeResource(connection, preparedStatement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
