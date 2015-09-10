package net.shinc.formbean.common;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

public class NickForm {

	@NotNull
	private List<Map> nickList;

	public List<Map> getNickList() {
		return nickList;
	}

	public void setNickList(List<Map> nickList) {
		this.nickList = nickList;
	}
	
}
