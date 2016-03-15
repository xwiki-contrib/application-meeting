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

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xwiki.test.ui.po.ViewPage;

/**
 * Represents the meeting home page.
 */
public class MeetingHomePage extends ViewPage
{
    @FindBy(xpath = "//a[@class = 'action add' and . = 'Add new entry']")
    private WebElement addMeetingAppButton;

    /**
     * Opens the meeting home page.
     *
     * @return the meeting home page
     */
    public static MeetingHomePage gotoPage()
    {
        getUtil().gotoPage("Meeting", "WebHome");
        return new MeetingHomePage();
    }

    public void clickAddNewEntryLink()
    {
        addMeetingAppButton.click();
    }

    public void setEntryName(String name)
    {
        WebElement nameInput = getDriver()
            .findElementWithoutWaiting(By.xpath("//div[@id = 'entryNamePopup']//input[@type = 'text']"));
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public MeetingEntryInlinePage clickAddEntry()
    {
        WebElement addButton = getDriver()
            .findElementWithoutWaiting(By.xpath("//div[@id = 'entryNamePopup']//input[@type = 'image']"));
        addButton.click();
        return new MeetingEntryInlinePage();
    }
}
