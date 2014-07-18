/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.contrib.meeting.test.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.mail.Message;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.xwiki.test.ui.AbstractTest;
import org.xwiki.test.ui.SuperAdminAuthenticationRule;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * UI tests for the Meeting application feature.
 */
public class MeetingTest extends AbstractTest
{
    @Rule
    public SuperAdminAuthenticationRule authenticationRule = new SuperAdminAuthenticationRule(getUtil(), getDriver());

    private GreenMail mail;

    @Before
    public void startMail()
    {
        this.mail = new GreenMail(ServerSetupTest.SMTP);
        this.mail.start();
    }

    @After
    public void stopMail()
    {
        if (this.mail != null) {
            this.mail.stop();
        }
    }

    @Test
    public void testMeetingSendInvitation() throws Exception
    {
        // Configure the SMTP host/port for the wiki so that it points to GreenMail.
        getUtil().addObject("XWiki", "XWikiPreferences", "XWiki.XWikiPreferences");
        getUtil().updateObject("XWiki", "XWikiPreferences", "XWiki.XWikiPreferences", 0, "smtp_port", 3025);
        getUtil().updateObject("XWiki", "XWikiPreferences", "XWiki.XWikiPreferences", 0, "smtp_server", "localhost");

        // Create some participants to meetings
        getUtil().createUser("participant1", "password", getUtil().getURLToNonExistentPage(), "email",
            "participant1@xwiki.org", "first_name", "participant1", "last_name", "Doe");
        getUtil().createUser("participant2", "password", getUtil().getURLToNonExistentPage(), "email",
            "participant2@xwiki.org", "first_name", "participant2", "last_name", "Doe");

        // Create user to be Leader of meeting
        getUtil().createUserAndLogin("JohnDoe", "password", "email", "john@xwiki.org", "first_name", "John",
            "last_name", "Doe");

        getUtil().gotoPage("Meeting", "WebHome");

        //// Remove existing meeting
        getUtil().deletePage("Meeting", "Meeting01");
        getUtil().createPage("Meeting", "Meeting01", null, "Meeting 01");

        // Configure the SMTP host/port for the wiki so that it points to GreenMail.
        getUtil().addObject("Meeting", "Meeting01", "Meeting.MeetingClass");
        getUtil().updateObject("Meeting", "Meeting01", "Meeting.MeetingClass", 0, "meetingTitle", "Meeting 01");
        getUtil().updateObject("Meeting", "Meeting01", "Meeting.MeetingClass", 0, "startDate", getDateTomorrow());
        getUtil().updateObject("Meeting", "Meeting01", "Meeting.MeetingClass", 0, "place", "Paris");
        getUtil()
            .updateObject("Meeting", "Meeting01", "Meeting.MeetingClass", 0, "description", "First meeting in paris");
        getUtil().updateObject("Meeting", "Meeting01", "Meeting.MeetingClass", 0, "leader", "XWiki.JohnDoe");

        getUtil().updateObject("Meeting", "Meeting01", "Meeting.MeetingClass", 0, "duration", "60");
        getUtil().updateObject("Meeting", "Meeting01", "Meeting.MeetingClass", 0, "participants",
            "XWiki.participant1,XWiki.participant2");
        getUtil().updateObject("Meeting", "Meeting01", "Meeting.MeetingClass", 0, "status", "value1");

        // Send email notification
        getUtil().findElementWithoutWaiting(getDriver(), By.id("sendNotification")).findElement(By.tagName("textarea"))
            .sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        getUtil().findElementWithoutWaiting(getDriver(), By.id("sendNotification")).submit();

        // Verify that the mail has been received.
        this.mail.waitForIncomingEmail(10000L, 2);
        assertEquals(2, this.mail.getReceivedMessages().length);
        assertEquals("You are invited to participate in a meeting", this.mail.getReceivedMessages()[0].getSubject());
        assertEquals("You are invited to participate in a meeting", this.mail.getReceivedMessages()[1].getSubject());
    }

    private String getDateTomorrow()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(calendar.getTime());
    }
}
