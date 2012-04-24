/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.sslr.squid.checks;

import com.sonar.sslr.test.miniC.MiniCGrammar;
import org.junit.Rule;
import org.junit.Test;

import static com.sonar.sslr.squid.metrics.ResourceParser.scanFile;

public class AbstractCommentRegularExpressionCheckTest {

  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  private static class EmptyCommentRegularExpressionCheck extends AbstractCommentRegularExpressionCheck<MiniCGrammar> {

    @Override
    public String getRegularExpression() {
      return "";
    }

    @Override
    public String getMessage() {
      return "Empty regular expression.";
    }

  }

  @Test
  public void emptyCommentRegularExpresssionCheck() {
    checkMessagesVerifier.verify(scanFile("/checks/commentRegularExpression.mc", new EmptyCommentRegularExpressionCheck()).getCheckMessages());
  }

  private static class CaseInsensitiveCommentRegularExpressionWithResultsCheck extends AbstractCommentRegularExpressionCheck<MiniCGrammar> {

    @Override
    public String getRegularExpression() {
      return "(?i).*TODO.*";
    }

    @Override
    public String getMessage() {
      return "Avoid TODO.";
    }

  }

  @Test
  public void caseInsensitiveCommentRegularExpressionWithResultsCheck() {
    checkMessagesVerifier.verify(scanFile("/checks/commentRegularExpression.mc", new CaseInsensitiveCommentRegularExpressionWithResultsCheck()).getCheckMessages())
        .next().atLine(3).withMessage("Avoid TODO.")
        .next().atLine(5)
        .next().atLine(7);
  }

  private static class CaseSensitiveCommentRegularExpressionWithResultsCheck extends AbstractCommentRegularExpressionCheck<MiniCGrammar> {

    @Override
    public String getRegularExpression() {
      return ".*TODO.*";
    }

    @Override
    public String getMessage() {
      return "Avoid TODO.";
    }

  }

  @Test
  public void caseSensitiveCommentRegularExpressionWithResultsCheck() {
    checkMessagesVerifier.verify(scanFile("/checks/commentRegularExpression.mc", new CaseSensitiveCommentRegularExpressionWithResultsCheck()).getCheckMessages())
        .next().atLine(3).withMessage("Avoid TODO.");
  }

}