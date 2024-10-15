package jdbc_user;

import java.util.Scanner;

public class UserMain {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		UserCrud crud = new UserCrud();
		boolean flag=true;
		while (flag) {
			System.out.println("Welcome in User Database \n1.Signup \n2.Login \n3.Exit");
			System.out.println("Enter your choice");
			int op = scanner.nextInt();
			switch (op) {
			case 1: {
				System.out.println("Enter the ID");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter the name");
				String name = scanner.nextLine();
				System.out.println("Enter the phone number");
				long phone = scanner.nextLong();
				scanner.nextLine();
				System.out.println("Enter the email");
				String email = scanner.nextLine();
				System.out.println("Enter the password");
				String password = scanner.nextLine();

				User user = new User();
				user.setId(id);
				user.setName(name);
				user.setPhone(phone);
				user.setEmail(email);
				user.setPassword(password);

				int result = crud.singUp(user);
				if (result != 0) {
					System.out.println("SignUp Successful");
					break;
				} else {
					System.out.println("SignUp failed plese re-enter data");
				}

				break;
			}
			case 2: {
				scanner.nextLine();
				System.out.println("Enter the email");
				String email = scanner.nextLine();
				System.out.println("Enter the password");
				String password = scanner.nextLine();
				String dbPass = crud.getPassword(email);
	
				boolean flag1=true;
				while (flag1) {
					if (dbPass != null) {
						if (dbPass.equals(password)) {
							System.out.println("1.Display Info \n2.Display Password \n3.Update Password \n4.Forgot Password \n5.Logout" );
							System.out.println("Enter your choice");
							int op2 = scanner.nextInt();
							switch (op2) {
							case 1:{
								User user=crud.getInfo(email);
								System.out.println("ID: "+user.getId());
								System.out.println("Name: "+user.getName());
								System.out.println("Phone No: "+user.getPhone());
								System.out.println("Email: "+user.getEmail());
								System.out.println("Password: "+user.getPassword());
								
							}
							break;
							case 2: {
								String dbPassword = crud.getPassword(email);
								System.out.println("Your password is: " + dbPassword);
							}
								break;

							case 3: {
								int i = 0;
								while (i < 3) {
									scanner.nextLine();
									System.out.println("Enter the new password");
									String updatePass1 = scanner.nextLine();
									System.out.println("Please re-enter password");
									String updatePass2 = scanner.nextLine();
									if (updatePass1.equals(updatePass2)) {
										int result = crud.updatePassword(email, updatePass2);
										if (result != 0) {
											System.out.println("Password Update");
											break;
										} else {
											System.out.println("Failed to update password");
										}
									} else {
										System.out.println("Please enter update password and re-enter password as same");
										i++;
									}
								}
								System.out.println("Your limit is reached");
		
							}
								break;
							case 4:
							{
								scanner.nextLine();
								System.out.println("Enter the new password");
								String pass=scanner.nextLine();
								int result = crud.updatePassword(email,pass);
								if(result!=0) {
								System.out.println("Password Updated");
								}
								else
								{
									System.out.println("Password not update");
								}
							}
							break;
							case 5:
							{
								System.out.println("Logout Successful");
								flag1=false;
							}
							}
						} else {
							System.out.println("Please check credentials");
						}
					} else {
						System.out.println(email + " this mail is not registered");
					}
				}
			}
			break;
			case 3: {
				System.out.println("Exit Successful");
				flag=false;		
				break;
			}

			}

		}

	}

}
