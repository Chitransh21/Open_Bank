package bankpackage;

import java.sql.*;
import java.io.*;
 class Bank
{	
public static void main(String []args)throws IOException
	{
		
		int ch;// for user choice switch case
		
		try
		{
			JDBC jd = new JDBC();
			System.out.println("\n\n***** Banking Management System*****\n\n");
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			do
			{
			System.out.print("Press 1:- For Open a Account in our Bank\t\t");
			System.out.println("Press 2:- Checking Balance");
			System.out.print("Press 3:- Deposit Money \t\t\t\t");
			System.out.println("Press 4:- Withdraw Money ");
			System.out.println("Press 5:- EXIT\n\n");
			System.out.println("enter your choice");
			ch = Integer.parseInt(br.readLine());
			switch(ch)
			{
			
			//**************//inserting records in customer table//*****************
			case 1:			    
				System.out.println("enter the name of the customer");
				String name=br.readLine();
				System.out.println("enter the phone no. of the customer");
				String phn=br.readLine();
				System.out.println("enter the initial balance");
				String bal=br.readLine();
				System.out.println("enter the aadhar no of the customer");
				String aadhar=br.readLine();

				String AC_NO="999"+aadhar.substring(1, 5)+aadhar.substring(7,11);
				
				try {
				String insstr="insert into CUSTOMER values('"+ AC_NO+"','"+name+"','"+phn+"',"+bal+",'"+ aadhar+"')";// sql query
				
				jd.insert(insstr);
				
				System.out.println("Thankyou for opening acount "+name+" your Account  No is "+ AC_NO+ "\n"+ "please do not share your account no \n\n");
				}
				catch(Exception e  ) {
					System.out.println("you have entered wrong details"+ e);
				}
					
				break;
				
				//********************//Show Account Details of a Customer//***************
			case 2: 
				System.out.println("enter the Account No of the customer");
				String ACC=br.readLine(); 
				String sqlstr2="select * from CUSTOMER where AC_NO='"+ACC+"'";
				try {
				ResultSet rs2= jd.exct(sqlstr2); 
			
				
				
				int pk = 0;
				while(rs2.next())
				{	
					System.out.print("A/C NO. :- "+rs2.getString(1)+"\t\t");// these indices are the column number of the column
					System.out.print("Name:- "+rs2.getString(2)+"\n");
					System.out.print("Mob. no.:- "+rs2.getString(3)+"\t\t");
					System.out.print("Balance in :- "+rs2.getString(4)+"\n");
					System.out.print("Aadhar No.:- "+rs2.getString(5)+"\n");
					System.out.println();
					pk+=1;
				}
				if(pk == 0) {
					System.out.println("You Have Entered Wrong Account Details");
				}
				}
				catch(Exception e) {
					System.out.println(e);
				}
			break;
			
			//*******//Deposit Money to an Account//******************************
			
			case 3:
				System.out.println("enter the account number");
				 ACC=br.readLine();
				
				String sqlstr31="select CUR_BALANCE from CUSTOMER where AC_NO='"+ACC+"'";			
				ResultSet rs3=jd.exct(sqlstr31);
				int pk = 0;
				while(rs3.next()) {
					pk+=1;
				}
				if(pk == 0) {
					System.out.println("You Have Entered Wrong Account Number \n");
					continue;
				}
				System.out.println("enter the amount to be deposited");
				int amt3=Integer.parseInt(br.readLine());
				if(amt3 == 0) {
					System.out.println("Please Enter Valid Amount\n");
					continue;
				}
//				System.out.println("Previous Balance is "rs3.getString("CUR_BALANCE"));
				
				
				String updstr="update CUSTOMER set CUR_BALANCE=CUR_BALANCE+"+amt3+" where AC_NO='"+ACC+"'";// sql query
			    jd.insert(updstr);
				
				String sqlstr32="select CUR_BALANCE from CUSTOMER where AC_NO='"+ACC+"'";
				ResultSet rs32=jd.exct(sqlstr32);
				
				System.out.print("Updated balance is: \t");
				while(rs32.next())
					System.out.println(rs32.getString("CUR_BALANCE")+"\n");
				
			break;
			
			//*****************//Withdraw Money from an Account//************************
			
			case 4:
				int bal4=0;
				System.out.println("enter the account number");
				String acc4=br.readLine();
				
				String sqlstr41="select CUR_BALANCE from CUSTOMER where AC_NO='"+acc4+"'";
				ResultSet rs41=jd.exct(sqlstr41);
				
				int pk1 = 0;
				while(rs41.next()) {
					pk1+=1;
					bal4=Integer.parseInt(rs41.getString("CUR_BALANCE"));
				}
				if(pk1 == 0) {
					System.out.println("You Have Entered Wrong Account Number \n");
					continue;
				}    
				System.out.println("enter the amount to be withdraw");
				int amt4=Integer.parseInt(br.readLine());
				System.out.println("Previous balance is: "+bal4);
				
//				while(rs41.next())
//				{
//					System.out.println(rs41.getString("CUR_BALANCE")+"\n");
//					
//				}
				if(bal4>=amt4)
				{
					updstr="update CUSTOMER set CUR_BALANCE=CUR_BALANCE-"+amt4+" where AC_NO='"+acc4+"'";// sql query
					jd.insert(updstr); 
					
					String sqlstr42="select CUR_BALANCE from CUSTOMER where AC_NO='"+acc4+"'";
					ResultSet rs42=jd.exct(sqlstr42);// ResultSet reference is a matrix
					System.out.print("Updated balance is:  ");
					while(rs42.next())
					System.out.println(rs42.getString("CUR_BALANCE")+"\n");
				}
				else
					System.out.println("!!!!! Current Balance is Lesser than Enterd Amount !!!!!\n");
			break;
			
			//***************//exit case//*************************
			case 5: 
				jd.closeconection();
				System.out.println("\nThank you\n");
				System.exit(0);
				break;
			default:
				System.out.println("\nWrong choice\n");
				}// end of switch case
			
			
			System.out.println("\n");
			}while(ch<5);// end of do block
						
		}// end of try block
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}// end of main method
}// end of public class