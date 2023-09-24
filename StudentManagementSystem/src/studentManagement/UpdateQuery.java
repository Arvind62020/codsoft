package studentManagement;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import inJDBCutil.JDBCUtil;

public class UpdateQuery extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
    private JTextField name;
    private JTextField rollNo;
    private JTextField totalMarks;
    private JTextField grade;
    private JTextField id;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateQuery frame = new UpdateQuery();
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
	public UpdateQuery() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 602, 524);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblName = new JLabel("NAME");
        lblName.setBounds(33, 51, 73, 21);
        contentPane.add(lblName);

        JLabel lblRollNo = new JLabel("ROLL NO");
        lblRollNo.setBounds(33, 103, 73, 21);
        contentPane.add(lblRollNo);

        JLabel lblTotalMarks = new JLabel("TOTAL MARKS");
        lblTotalMarks.setBounds(33, 145, 73, 21);
        contentPane.add(lblTotalMarks);

        JLabel lblGrade = new JLabel("GRADE");
        lblGrade.setBounds(33, 205, 73, 21);
        contentPane.add(lblGrade);

        name = new JTextField();
        name.setBounds(147, 52, 164, 19);
        contentPane.add(name);

        rollNo = new JTextField();
        rollNo.setBounds(147, 104, 164, 19);
        contentPane.add(rollNo);

        totalMarks = new JTextField();
        totalMarks.setBounds(147, 146, 164, 19);
        contentPane.add(totalMarks);

        grade = new JTextField();
        grade.setBounds(147, 206, 164, 19);
        contentPane.add(grade);

        JButton submit = new JButton("Update");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sname = name.getText();
                String srollNo = rollNo.getText();
                String stotalMarks = totalMarks.getText();
                String sgrade = grade.getText();
                int sid = Integer.parseInt(id.getText());
                //Double.parseDouble(textField.getText());

                if (sname.isEmpty() || srollNo.isEmpty() || stotalMarks.isEmpty() || sgrade.isEmpty()) {
                    // Show an error message if any of the fields is empty
                    JOptionPane.showMessageDialog(null, "All fields are required.");
                } else {
                    // All fields are filled, proceed with database insertion
                    insertStudentRecord(sname, srollNo, stotalMarks, sgrade,sid);
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
        
        JLabel lblNewLabel = new JLabel("ENTER ID*");
        lblNewLabel.setBounds(33, 26, 73, 13);
        contentPane.add(lblNewLabel);
        
        id = new JTextField();
        id.setBounds(147, 23, 164, 19);
        contentPane.add(id);
        id.setColumns(10);
    }

    // Method to insert student record into the database
    private void insertStudentRecord(String sname, String srollNo, String stotalMarks, String sgrade,int sid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtil.getJdbcConnection();
            if (connection != null) {
               // String mysqlQuery = "INSERT INTO student(name, rollNo, totalmarks, grade) VALUES (?, ?, ?, ?)";
                String mysqlQuery = "update student set name=?, rollNo=?, totalmarks=?, grade=? where id=?";
                preparedStatement = connection.prepareStatement(mysqlQuery);
                preparedStatement.setString(1, sname);
                preparedStatement.setString(2, srollNo);
                preparedStatement.setString(3, stotalMarks);
                preparedStatement.setString(4, sgrade);
                preparedStatement.setInt(5, sid);

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
