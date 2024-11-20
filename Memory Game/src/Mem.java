import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.*;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class Mem extends JFrame {

	private JPanel contentPane;
	Timer timer;
	JLabel enter_answer_label;
	JLabel single_StringJLabel;
	JButton SingleString_B;
	JButton One_Character_B;
	JButton Info_B;
	JLabel memory_Button;
	JButton submit_button;
	int count = 0;
	int count_2 = 0;
	Timer stopwatch;
	int delay = 1000;
	JLabel timeLeftLabel;
	boolean timer_stopped;
	boolean second_timer_stopped;
	boolean is_one_character;
	String single_string;
	boolean returned;
	int t_correct, t_wrong;
	
	char[] dict = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mem frame = new Mem();
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
	public Mem() {
		
		t_correct = 0;
		t_wrong = 0;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel How_many_chars_B = new JLabel("How many characters?");
		How_many_chars_B.setVisible(false);
		How_many_chars_B.setFont(new Font("Andalus", Font.PLAIN, 30));
		How_many_chars_B.setBounds(318, 171, 318, 34);
		contentPane.add(How_many_chars_B);
		
		SingleString_B = new JButton("Single String");
		
		SingleString_B.setBounds(218, 302, 115, 23);
		contentPane.add(SingleString_B);
		
		One_Character_B = new JButton("One character at a time");
		One_Character_B.setBounds(353, 302, 170, 23);
		contentPane.add(One_Character_B);
		
		Info_B = new JButton("Info");
		Info_B.setBounds(532, 302, 89, 23);
		contentPane.add(Info_B);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(328, 216, 46, 20);
		spinner.setVisible(false);
		contentPane.add(spinner);
		
		memory_Button = new JLabel("Memory Game");
		memory_Button.setFont(new Font("Andalus", Font.PLAIN, 30));
		memory_Button.setBounds(318, 115, 186, 34);
		contentPane.add(memory_Button);
		
		JButton go_Button = new JButton("Go");
		go_Button.setVisible(false);
		go_Button.setBounds(318, 243, 89, 23);
		contentPane.add(go_Button);
		
		JLabel durationLabel = new JLabel("Duration? (s)");
		durationLabel.setVisible(false);
		durationLabel.setFont(new Font("Andalus", Font.PLAIN, 30));
		durationLabel.setBounds(318, 349, 318, 34);
		contentPane.add(durationLabel);
		
		JSpinner duration_spinner = new JSpinner();
		duration_spinner.setVisible(false);
		duration_spinner.setBounds(328, 394, 30, 20);
		contentPane.add(duration_spinner);
		
		single_StringJLabel = new JLabel("");
		single_StringJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		single_StringJLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		single_StringJLabel.setBounds(232, 206, 357, 85);
		contentPane.add(single_StringJLabel);
		
		timeLeftLabel = new JLabel("");
		timeLeftLabel.setFont(new Font("Teko SemiBold", Font.BOLD, 30));
		timeLeftLabel.setBounds(329, 28, 115, 60);
		contentPane.add(timeLeftLabel);
		
		textField = new JTextField();
		textField.setBounds(339, 84, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		enter_answer_label = new JLabel("Enter what you just saw");
		enter_answer_label.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		enter_answer_label.setBounds(346, 40, 275, 38);
		contentPane.add(enter_answer_label);
		
		submit_button = new JButton("Submit");
		submit_button.setBounds(434, 83, 89, 23);
		contentPane.add(submit_button);
		
		JLabel singleStringLabel = new JLabel("");
		singleStringLabel.setFont(new Font("Consolas", Font.PLAIN, 37));
		singleStringLabel.setBounds(318, 257, 198, 60);
		contentPane.add(singleStringLabel);
		
		JButton goButtonForOneChar = new JButton("");
		goButtonForOneChar.setBounds(318, 277, 89, 23);
		contentPane.add(goButtonForOneChar);
		submit_button.setVisible(false);
		enter_answer_label.setVisible(false);
		textField.setVisible(false);
		
		goButtonForOneChar.setVisible(false);
		
		One_Character_B.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				is_one_character = true;
				//firstly clear the screen
				SingleString_B.setVisible(false);
				One_Character_B.setVisible(false);
				Info_B.setVisible(false);
				How_many_chars_B.setVisible(false);
				memory_Button.setVisible(false);
				
				//now show the spinner, and the label
				spinner.setVisible(true);
				How_many_chars_B.setVisible(true);
				//go_Button.setVisible(true);
				goButtonForOneChar.setVisible(true);
				goButtonForOneChar.setText("Go");
				//durationLabel.setVisible(true);
				//duration_spinner.setVisible(true);
				
				//is_one_character = true;
				
				//start_secondTimer(number_of_chars, single_StringJLabel);
				//start_secondTimer(5, single_StringJLabel);
			}
		});
		
		goButtonForOneChar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//can we make use of the same spinner and varname?
				if((int) spinner.getValue() <= 0) {
					JOptionPane.showMessageDialog(new JFrame(), "You must choose at least one character!", "ERROR",	  JOptionPane.ERROR_MESSAGE);
				}else {
					int number_of_chars = (int) spinner.getValue();
					//make everything on the screen invisible
					spinner.setVisible(false);
					How_many_chars_B.setVisible(false);
					goButtonForOneChar.setVisible(false);
					
					
					start_secondTimer(number_of_chars, single_StringJLabel);
				}
				
				
				
			}
		});
		
		
		go_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if((int) spinner.getValue() <= 0 || (int) duration_spinner.getValue() <= 0) {
					JOptionPane.showMessageDialog(new JFrame(), "ERROR: You must have at least one character, and a time set to at least one second", "ERROR",	  JOptionPane.ERROR_MESSAGE);
					//System.exit(ERROR);
				}else {
					int number_of_chars = (int) spinner.getValue();
					int duration_in_secs = (int) duration_spinner.getValue();
					
						
						
					
					
					//System.out.println(number_of_chars);
					
					//System.out.println("lets go!");
					//remove objects
					go_Button.setVisible(false);
					How_many_chars_B.setVisible(false);
					spinner.setVisible(false);
					duration_spinner.setVisible(false);
					durationLabel.setVisible(false);
					
					//first_run = true;
					
					//System.out.println("has been run");
					//System.out.println("label_to_hide visibility before: " + single_StringJLabel.isVisible());
					single_StringJLabel.setVisible(true);
					//System.out.println("label_to_hide visibility after: " + single_StringJLabel.isVisible());
					//System.out.println("single string j label visibility: " + single_StringJLabel.isVisible());
					//single_StringJLabel.setBounds(50, 50, 200, 30);  // (x, y, width, height)
					single_StringJLabel.setText("Visible Label");
					
					
					//System.out.println("duration = " + duration_in_secs);
					int noc = (int)spinner.getValue();
					long duration_in_millis = duration_in_secs * 1000;
					//System.out.println(duration_in_millis);
					/*
					spinner.setVisible(true);
					How_many_chars_B.setVisible(true);
					go_Button.setVisible(true);
					*/
					single_string = "";
					Random random = new Random();
					int random_num = 0;
					for(int i = 0; i < number_of_chars; i++) {
						//System.out.println("test");
						random_num = random.nextInt(0,dict.length - 1);
						//System.out.println("random index chosen: " + random_num);
						single_string += dict[random_num];
						
					}
					single_StringJLabel.setText(single_string);
					//System.out.println("chosen random string: " + single_string);
					//System.out.println("single string here is " + single_string);
					timeLeftLabel.setVisible(true);
					//System.out.println("timeleftlabel visibility is CURRENTLY " + timeLeftLabel.isVisible());
					///System.out.println("text in timeleftlabel is CURRENTLY " + timeLeftLabel.getText());
					
					//single_StringJLabel.setText("aaaaaaaaaaaaaaaaaaaaa");
					
					//single_StringJLabel.setText("aaaaa");
					//System.out.println("starting timer...");
					
					startTimer(duration_in_secs, single_StringJLabel);
					
				
						//System.out.println("timer has been init?");
						timeLeftLabel.setVisible(true);
						timeLeftLabel.setText("affected by the go button AL");
					
					
					
					
					
					
					//after timer is done, hide the string
					//single_StringJLabel.setVisible(false);
					
					//single_StringJLabel.setVisible(false);
				}
				
			}
		});
		
		Info_B.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 try {
					Desktop.getDesktop().browse(new URI("https://github.com/ReverseEntropy"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		SingleString_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//make everything invisible - proceed to next scene
				SingleString_B.setVisible(false);
				One_Character_B.setVisible(false);
				Info_B.setVisible(false);
				How_many_chars_B.setVisible(false);
				memory_Button.setVisible(false);
				
				//now show the spinner, and the label
				spinner.setVisible(true);
				How_many_chars_B.setVisible(true);
				go_Button.setVisible(true);
				durationLabel.setVisible(true);
				duration_spinner.setVisible(true);
				
				//otherwise it would display all the option panes from the previous listeners
				for (ActionListener al : submit_button.getActionListeners()) {
				    submit_button.removeActionListener(al);
				}
				
				submit_button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(textField.getText().equals(single_string)) {
							t_correct++;
							JOptionPane.showMessageDialog(new JFrame(), "Answer matches original string! " + "(" + single_string + ")" + "\ntotal times correct = " + t_correct + "\ntotal times wrong = " + t_wrong,  "Correct",	 JOptionPane.PLAIN_MESSAGE);
						}else {
							//accuracy score calculation
							String test = textField.getText();
							double score = 0;
							double ct = 0;
							for(int i = 0; i < test.length(); i++) {
								if(test.charAt(i) == single_string.charAt(i)) {
									System.out.println("char found correct");
									ct++;
								}
							}
							
							score = (ct / single_string.length()) * 100;
							
							//System.out.println("score = " + score);
							//System.out.println("correct points = " + ct);
							
							t_wrong++;
							JOptionPane.showMessageDialog(new JFrame(), "Answer does not match original string" + "(" + single_string + ")" + "\ntotal times correct = " + t_correct + "\ntotal times wrong = " + t_wrong, "Incorrect - " +  score + "% correct",	  JOptionPane.ERROR_MESSAGE);
						}
						//hide components on screen
						textField.setVisible(false);
						submit_button.setVisible(false);
						enter_answer_label.setVisible(false);
						
						//set everything to empty string
						textField.setText("");
						single_string = "";
						
						
						//now draw original program, back to where we started
						memory_Button.setVisible(true);
						SingleString_B.setVisible(true);
						One_Character_B.setVisible(true);
						Info_B.setVisible(true);
						
						
					}
				});
				
			}
		});
	}
	
	public void start_secondTimer(int countPassed, JLabel l_to_hide) {
		//generate random string (again - should've used functional programming here)
		int number_of_chars = 5;
		single_string = "";
		//System.out.println("countPassed = " + countPassed);
		Random random = new Random();
		int random_num = 0;
		for(int i = 0; i < countPassed; i++) {
			//System.out.println("test");
			random_num = random.nextInt(0,dict.length - 1);
			//System.out.println("random index chosen: " + random_num);
			single_string += dict[random_num];
			
		}
		//System.out.println("single_string = "  + single_string);
		
		
		
		
		//System.out.println("random string =  " + single_string);
		
		ActionListener second_aL = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(count_2 == 0 && !second_timer_stopped) {
					//System.out.println("count_2 = 0");
					stopwatch.stop();
					single_StringJLabel.setText("");
					second_timer_stopped = true;

				}else {
					//System.out.println(Integer.toString(count_2));
					single_StringJLabel.setText(Character.toString(single_string.charAt(single_string.length() - count_2)));
					count_2--;
				}
				
				if(second_timer_stopped) {
					//System.out.println("second timer stopped");
					textField.setVisible(true);
					enter_answer_label.setVisible(true);
					submit_button.setVisible(true);
					second_timer_stopped = false;
					//otherwise it would display all the option panes from the previous listeners
					for (ActionListener al : submit_button.getActionListeners()) {
					    submit_button.removeActionListener(al);
					}
					submit_button.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							//accuracy score calculation
							double score = 0;
							double ct = 0;
							for(int i = 0; i < single_string.length(); i++) {
								if(textField.getText().charAt(i) == single_string.charAt(i)) {
									//System.out.println("char found correct");
									ct++;
								}
							}                                                                           
							     
							score = (ct / textField.getText().length()) * 100;
							
							//System.out.println("score = " + score);
							//System.out.println("correct points = " + ct);
							
							//t_wrong++;
							
							if(textField.getText().equals(single_string)) {
								//System.out.println("is this the one char at a time");
								t_correct++;
								JOptionPane.showMessageDialog(new JFrame(), "Answer matches original string! " + "(" + single_string + ")" + "\ntotal times correct = " + t_correct + "\ntotal times wrong = " + t_wrong,  "Correct",	 JOptionPane.PLAIN_MESSAGE);
							}else {
								t_wrong++;
								JOptionPane.showMessageDialog(new JFrame(), "Answer does not match original string" + "(" + single_string + ")" + "\ntotal times correct = " + t_correct + "\ntotal times wrong = " + t_wrong, "Incorrect - " +  score + "% correct",	  JOptionPane.ERROR_MESSAGE);
							}
							//hide components on screen
							textField.setVisible(false);
							submit_button.setVisible(false);
							enter_answer_label.setVisible(false);
							
							//set everything to empty string
							textField.setText("");
							single_string = "";
							
							
							//now draw original program, back to where we started
							memory_Button.setVisible(true);
							SingleString_B.setVisible(true);
							One_Character_B.setVisible(true);
							Info_B.setVisible(true);
							
							
						}
					});
				}
				
				
				
			}
			
			
			
		};
		
		stopwatch = new Timer(delay, second_aL);
		stopwatch.setInitialDelay(0);
		stopwatch.start();
		count_2 = countPassed;
	}
	
	public void startTimer(int countPassed, JLabel label_to_hide) {
		ActionListener actionL = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(count == 0) {
					//System.out.println("count = 0");
					stopwatch.stop();
					timer_stopped = true;
					
				}else {
					//System.out.println("You have " + count + " seconds remaining");
					timeLeftLabel.setVisible(true);
					//System.out.println("count CURRENTLY EQUALS " + Integer.toString(count));
					timeLeftLabel.setText(Integer.toString(count));
					
					//System.out.println("text in the time left label: " + timeLeftLabel.getText());
					count--;
				}
				
				if(timer_stopped) {
					//reset everything
					contentPane.add(timeLeftLabel);
					timeLeftLabel.setText("");
					label_to_hide.setText("");
					//timeLeftLabel.setVisible(false);
					
					enter_answer_label.setVisible(true);
					textField.setVisible(true);
					submit_button.setVisible(true);
					//System.out.println("string printed in the timer method");
					timer_stopped = false;
					
					
				}else {
					//System.out.println("still thinks timer is running...");
				}
				
			
				
			}
		};
		
		stopwatch = new Timer(delay, actionL);
		stopwatch.setInitialDelay(0);
		stopwatch.start();
		count = countPassed;
		
		//System.out.println("finished?");
		contentPane.revalidate();
		contentPane.repaint();
		
	}
	
	
	
	
	public boolean is_answer_correct(String attempt, String answer) {
		if(attempt.equals(answer)) {
			return true;
		}
		return false;
	}
}
