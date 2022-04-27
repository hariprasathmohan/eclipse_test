//$Id$

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/*
import com.hari.Models.AccountModel;
import com.hari.Models.BranchModel;
import com.hari.Models.CustomerModel;
import com.hari.Models.FixedDepositModel;
import com.hari.Utils.Utils;*/

public class Database {/*
						 * private static Database database=new Database(); private List<BranchModel> branchList; private Map<String, AccountModel> accounts; private List<CustomerModel> customers;
						 * private Map<String, FixedDepositModel> fixedList; private Database() { branchList=new ArrayList<>(); accounts= new HashMap<>(); customers= new ArrayList<>(); fixedList=new
						 * HashMap<>(); doAutoUpdate(); } public static Database getInstance() { if(database== null) return new Database(); return database; }
						 * 
						 * public void addBank(BranchModel branch) { if(branch!=null) branchList.add(branch); } public String getBranch(String code) { for(BranchModel branch:branchList) {
						 * if(branch.getBranchCode().equals(code)) return branch.getBranchName(); } return null; } public String getBank(int bankId) { for(BranchModel branch:branchList) {
						 * if(branch.getBankId()==bankId) return branch.getBankName(); } return null; } public BranchModel getBranch(String bankName,String branchName) { for(BranchModel
						 * branch:branchList) { if(branch.getBankName().equals(bankName)&& branch.getBranchName().equals(branchName)) return branch; } return null; } public void addAccount(String
						 * accountNumber,AccountModel account) { if(accountNumber!=null && account!=null) accounts.put(accountNumber, account); } public AccountModel getAccount(String accountNumber) {
						 * if(accountNumber!=null) return accounts.get(accountNumber); return null; } public void addCustomer(CustomerModel customer) { if(customer!=null) { customers.add(null); } }
						 * public List<CustomerModel> getAllCustomers() { return customers; } public int customerDataSize() { return customers.size(); } public int accountDataSize() { return
						 * accounts.size(); }
						 * 
						 * public List<String> getAllBank(){ List<String> bankName=new ArrayList<>(); for(BranchModel branch:branchList) { bankName.add(branch.getBankName()); } Set<String>
						 * bankNames=new HashSet<>(bankName); bankName.clear(); bankName.addAll(bankNames); return bankName; }
						 * 
						 * public List<String> getBranches(String bankName){ List<String> branchName=new ArrayList<>(); for(BranchModel branch:branchList) { if(branch.getBankName().equals(bankName))
						 * branchName.add(branch.getBranchName()); } return branchName; } public AccountModel validateUserId(String userId) { for(String keys:accounts.keySet()) {
						 * if(accounts.get(keys).getUserId().equals(userId)) return accounts.get(keys); } return null; } public boolean validateAccountNumber(String accountNumber) { for(String
						 * keys:accounts.keySet()) { if(keys.equals(accountNumber)) return true; } return false; } public boolean validateIFSC(String IFSC) { for(BranchModel bank:branchList) {
						 * if(bank.getIFSC().equals(IFSC)) return true; } return false; } public boolean validateUser(String accountNumber , String IFSC) {
						 * 
						 * if(accounts.get(accountNumber).getBank().getIFSC().equals(IFSC)) return true;
						 * 
						 * return false;
						 * 
						 * } public void addFixedDepositAccount(String fdAccountNumber,FixedDepositModel fdModel) { if(fdAccountNumber!=null && fdModel!=null) { fixedList.put(fdAccountNumber,
						 * fdModel); } } public FixedDepositModel getFixedDepositAccount(String fdAccountNumber) { if(fdAccountNumber!=null) return fixedList.get(fdAccountNumber); return null; }
						 * public void doAutoUpdate() { for(String key:fixedList.keySet()) { String currentdate=Utils.currentDate(); Period diff =
						 * Period.between(LocalDate.parse(fixedList.get(key).getUpdatedAt()), LocalDate.parse(currentdate)); long monthDifference=diff.toTotalMonths(); if(monthDifference>0) { double
						 * balance=fixedList.get(key).getBalance(); balance+=balance*(fixedList.get(key).getInterest()/100)*monthDifference; fixedList.get(key).setBalance(balance);
						 * fixedList.get(key).setUpdatedAt(currentdate); } } }
						 */
}
