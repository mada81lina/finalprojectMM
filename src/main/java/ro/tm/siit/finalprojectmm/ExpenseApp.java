package ro.tm.siit.finalprojectmm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ExpenseApp {
	
	static Map<Date,List<Expense>> expenses = new HashMap<Date, List<Expense>>();
	
	public static void main(String[] args) {
		addExpense(new Expense("once"), ExpensesType.ONETIME);
		addExpense(new Expense("daily"), ExpensesType.DAILY);
		addExpense(new Expense("weekly"), ExpensesType.WEEKLY);
		addExpense(new Expense("monthly"), ExpensesType.MONTHLY);
		//expenses.toString();
		Date today;
		GregorianCalendar calendar = getCalendar();
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);
		
		while(calendar.get(Calendar.YEAR) == currentYear){
			List<Expense> todaysExpenses = expenses.get(today);
			if(todaysExpenses != null){
				System.out.print(today + "  ");
				for (Expense exp:todaysExpenses) {
					System.out.print(exp.getName() + "  ");
				}
			}
			System.out.println();
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
			today = calendar.getTime();
		}
		//System.out.println(expenses);
	}
	
	public static void addExpense(Expense expense, ExpensesType type){
		Date today;
//		GregorianCalendar calendar = new GregorianCalendar();
//		calendar.setTime(new Date());
//		calendar.set(Calendar.HOUR, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
//		calendar.set(Calendar.MILLISECOND, 0);
		GregorianCalendar calendar = getCalendar();
		today = calendar.getTime();
		int currentYear = calendar.get(Calendar.YEAR);
		
		switch(type){
		case DAILY:
			while(calendar.get(Calendar.YEAR) == currentYear){
				addExpenseAtDate(expense, today);
				calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
				today = calendar.getTime();
			}
			break;
		case WEEKLY:
			while(calendar.get(Calendar.YEAR) == currentYear){
				addExpenseAtDate(expense, today);
				calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
				today = calendar.getTime();
			}
			break;
		case MONTHLY:
			while(calendar.get(Calendar.YEAR) == currentYear){
				addExpenseAtDate(expense, today);
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
				today = calendar.getTime();
			}
			break;
		default:
			addExpenseAtDate(expense, today);
		}
	}
	
	public static void addExpenseAtDate(Expense expense, Date date){
		List<Expense> todaysExpenses = expenses.get(date);
		if(todaysExpenses == null){
			todaysExpenses = new ArrayList<Expense>();
		}
		todaysExpenses.add(expense);
		
		
		expenses.put(date, todaysExpenses);
	}
	
	public static GregorianCalendar getCalendar() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
		
	}

}
