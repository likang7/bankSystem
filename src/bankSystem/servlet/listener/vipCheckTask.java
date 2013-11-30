package bankSystem.servlet.listener;

import java.util.TimerTask;

import bankSystem.service.VIPBusinessService;

public class vipCheckTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		VIPBusinessService vbs = new VIPBusinessService();
		vbs.checkBalance();
		vbs.checkPaidback();
	}

}
