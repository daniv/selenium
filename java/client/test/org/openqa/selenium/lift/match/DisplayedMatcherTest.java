/*
Copyright 2012 Software Freedom Conservancy
Copyright 2012 Selenium committers

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package org.openqa.selenium.lift.match;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.lift.match.DisplayedMatcher.displayed;

public class DisplayedMatcherTest {

  @Rule public JUnitRuleMockery mockery = new JUnitRuleMockery();

  @Test
  public void testShouldNotFailForDisplayedWebElement() {
    WebElement element = createWebElementWithDisplayed(true);
    assertThat(element, is(displayed()));
  }

  @Test
  public void testShouldFailForNotDisplayedWebElement() {
    final WebElement element = createWebElementWithDisplayed(false);
    assertThat(element, is(not(displayed())));
  }

  private WebElement createWebElementWithDisplayed(final boolean displayed) {
    final WebElement element = mockery.mock(WebElement.class);
    mockery.checking(new Expectations() {
      {
        oneOf(element).isDisplayed();
        will(returnValue(displayed));
      }
    });
    return element;
  }
}
