package com.cf.pojo.group;

import java.util.List;

import com.cf.pojo.TbBbs;
import com.cf.pojo.TbBbsReply;

public class BlogReplys {

	private TbBbs Bbs;
	
	private List<TbBbsReply> replyList;

	public TbBbs getBbs() {
		return Bbs;
	}

	public void setBbs(TbBbs bbs) {
		Bbs = bbs;
	}

	public List<TbBbsReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<TbBbsReply> replyList) {
		this.replyList = replyList;
	}
}
