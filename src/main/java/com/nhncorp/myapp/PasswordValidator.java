package com.nhncorp.myapp;

public class PasswordValidator {
	 public boolean isValid(String password) {
         boolean result = false;
         int letterCnt = 0;
         int digitCnt = 0;

         for (int i = 0; i < password.length(); i++) {
              char c = password.charAt(i);
              if (Character.isLetter(c)) letterCnt++;
              else if (Character.isDigit(c)) digitCnt++;
         }

         // 8�ڸ� �̻��̰�(�Է� �� üũ������) ���ڿ� ���ڰ� �� �� ������ �־�� ��
         if (password.length() >= 8 && letterCnt > 0 && digitCnt > 0)
              result = true;

             return result;
         }

}
