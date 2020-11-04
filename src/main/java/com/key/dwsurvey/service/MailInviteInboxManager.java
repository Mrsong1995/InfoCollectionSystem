package com.key.dwsurvey.service;

import com.key.common.plugs.page.Page;
import com.key.dwsurvey.entity.MailInviteInbox;

import java.util.List;

public interface MailInviteInboxManager {
    public void save(MailInviteInbox entity);

    public Page<MailInviteInbox> findPage(Page<MailInviteInbox> page2,
                                          String surveyInviteId);

    public List<MailInviteInbox> findList(String mailInviteId);

    public MailInviteInbox getBySendcloudEmailId(String emailId);

	public MailInviteInbox getByEmail(String mailInviteId, String email);

	public void post(String mailInviteId);
	
}
