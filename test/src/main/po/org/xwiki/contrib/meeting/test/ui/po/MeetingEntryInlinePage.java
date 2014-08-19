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
package org.xwiki.contrib.meeting.test.ui.po;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.xwiki.test.ui.po.InlinePage;

/**
 * Represents the meeting entry inline page.
 */
public class MeetingEntryInlinePage extends InlinePage
{
    /**
     * The meeting entry title input field.
     */
    @FindBy(xpath = "//div[@class = 'xform']//input[@name = 'title']")
    private WebElement titleInput;

    /**
     * The meeting entry start date input field.
     */
    @FindBy(id = "Meeting.MeetingClass_0_startDate")
    private WebElement startDateInput;

    /**
     * The meeting entry duration hour input field.
     */
    @FindBy(id = "Meeting.MeetingClass_0_durationHour")
    private WebElement durationHourInput;

    /**
     * The meeting entry duration minutes input field.
     */
    @FindBy(id = "Meeting.MeetingClass_0_durationMinutes")
    private WebElement durationMinutesInput;

    /**
     * The meeting entry place input field.
     */
    @FindBy(id = "Meeting.MeetingClass_0_place")
    private WebElement placeInput;

    /**
     * The meeting entry description input field.
     */
    @FindBy(id = "Meeting.MeetingClass_0_description")
    private WebElement descriptionInput;

    /**
     * The meeting entry notes input field.
     */
    @FindBy(id = "Meeting.MeetingClass_0_notes")
    private WebElement notesInput;

    /**
     * The meeting entry status input field.
     */
    @FindBy(id = "Meeting.MeetingClass_0_status")
    private WebElement statusInput;

    /**
     * The meeting entry leader input field.
     */
    @FindBy(id = "Meeting.MeetingClass_0_leader")
    private WebElement leaderInput;

    /**
     * The meeting entry participants input field.
     */
    @FindBy(id = "Meeting.MeetingClass_0_participants")
    private WebElement participantsInput;

    /**
     * @return the value of the title input field
     */
    public String getTitle()
    {
        return titleInput.getAttribute("Value");
    }

    /**
     * Sets the value of the title input field.
     *
     * @param title the new meeting entry title
     */
    public void setTitle(String title)
    {
        titleInput.clear();
        titleInput.sendKeys(title);
    }

    /**
     * @return the value of the start date input field
     */
    public String getStartDate()
    {
        return startDateInput.getAttribute("Value");
    }

    /**
     * Sets the value of the start date input field.
     *
     * @param startDate the new meeting entry start date
     */
    public void setStartDate(String startDate)
    {
        startDateInput.clear();
        startDateInput.sendKeys(startDate);
    }

    /**
     * @return the value of the duration hour input field
     */
    public String getDurationHour()
    {
        Select durationHourSelect = new Select(durationHourInput);
        return durationHourSelect.getFirstSelectedOption().getText();
    }

    /**
     * Sets the value of the duration hour input field.
     *
     * @param duration the new meeting entry duration hour
     */
    public void setDurationHour(String duration)
    {
        Select durationHourSelect = new Select(durationHourInput);
        durationHourSelect.selectByValue(duration);
    }

    /**
     * @return the value of the duration minutes input field
     */
    public String getDurationMinutes()
    {
        Select durationMinutesSelect = new Select(durationMinutesInput);
        return durationMinutesSelect.getFirstSelectedOption().getText();
    }

    /**
     * Sets the value of the duration minutes input field.
     *
     * @param duration the new meeting entry duration minutes
     */
    public void setDurationMinutes(String duration)
    {
        Select durationMinutesSelect = new Select(durationMinutesInput);
        durationMinutesSelect.selectByValue(duration);
    }

    /**
     * @return the value of the place input field
     */
    public String getPlace()
    {
        return placeInput.getAttribute("Value");
    }

    /**
     * Sets the value of the place input field.
     *
     * @param place the new meeting entry place
     */
    public void setPlace(String place)
    {
        placeInput.clear();
        placeInput.sendKeys(place);
    }

    /**
     * @return the value of the description input field
     */
    public String getDescription()
    {
        return placeInput.getAttribute("Value");
    }

    /**
     * Sets the value of the description input field.
     *
     * @param description the new meeting entry description
     */
    public void setDescription(String description)
    {
        descriptionInput.clear();
        descriptionInput.sendKeys(description);
    }

    /**
     * @return the value of the notes input field
     */
    public String getNotes()
    {
        return notesInput.getAttribute("Value");
    }

    /**
     * Sets the value of the notes input field.
     *
     * @param notes the new meeting entry notes
     */
    public void setNotes(String notes)
    {
        notesInput.clear();
        notesInput.sendKeys(notes);
    }

    /**
     * @return the value of the notes input field
     */
    public String getStatus()
    {
        Select notesSelect = new Select(notesInput);
        return notesSelect.getFirstSelectedOption().getText();
    }

    /**
     * Sets the value of the status input field.
     *
     * @param status the new meeting entry status
     */
    public void setStatus(String status)
    {
        Select statusSelect = new Select(statusInput);
        statusSelect.selectByVisibleText(status);
    }

    /**
     * Sets the value of the leader input field.
     *
     * @param leader the new meeting entry leader
     */
    public void setLeader(String leader)
    {
        leaderInput.clear();
        leaderInput.sendKeys(leader);
        waitUntilElementIsVisible(By.className("suggestItem"));
        getUtil().findElementWithoutWaiting(getDriver(), By.className("suggestItem")).click();
        waitUntilElementDisappears(By.className("suggestItem"));
    }

    /**
     * Sets the value of the leader input field.
     *
     * @param participants the new meeting entry participants
     */
    public void setParticipants(List<String> participants)
    {
        for (String participant : participants) {
            participantsInput.sendKeys(participant);
            waitUntilElementIsVisible(By.className("suggestItem"));
            getUtil().findElementWithoutWaiting(getDriver(), By.className("suggestItem")).click();
            waitUntilElementDisappears(By.className("suggestItem"));
        }
    }
}
