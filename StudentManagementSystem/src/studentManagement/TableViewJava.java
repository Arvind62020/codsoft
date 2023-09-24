package studentManagement;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class TableViewJava extends JFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableViewJava frame = new TableViewJava();
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
	public TableViewJava() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 530);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 27, 562, 431);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		// Create a DefaultTableModel with column names
        DefaultTableModel model = new DefaultTableModel(
            new String[] { "ID", "NAME", "ROLL NO", "TOTAL MARKS", "GRADE" },
            0  // 0 indicates initial row count (empty)
        );

        table = new JTable(model);
        scrollPane.setViewportView(table);
        SelectQuery obj =new SelectQuery();
        // Add rows using a for loop
        for (int i = 1; i <=5; i++) {
        	ArrayList<String> myArrayList=obj.featch(i);
        	if (myArrayList.get(0)=="not") {
				break;
			}
				
			
            Object[] rowData = { myArrayList.get(0),myArrayList.get(1),myArrayList.get(2),myArrayList.get(3),i };
            model.addRow(rowData);
        }
    }

	
		
	}


