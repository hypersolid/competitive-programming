class Solution {

  final int BASE = 10;
  final int NO_RESULT = -1;
  int[] digits = new int[10];
  int totalDigits;

  int toDigitArray(int n) {
    int totalDigits = 0;
    while (n > 0) {
      int digit = n % BASE;
      digits[totalDigits++] = digit;
      n /= BASE;
    }
    return totalDigits;
  }

  void swap(int i, int j) {
    int tmp = digits[i];
    digits[i] = digits[j];
    digits[j] = tmp;
  }

  int arrayToNumber() {
    int result = 0;
    int power = 1;
    for (int i = 0; i < digits.length; i++) {
      result += digits[i] * power;
      power *= 10;
    }
    return result;
  }

  int pivot() {
    for (int i = 1; i < totalDigits; i++) {
      if (digits[i - 1] > digits[i]) {
        return i;
      }
    }
    return NO_RESULT;
  }

  public int nextGreaterElement(int n) {
    if (n == 0) {
      return NO_RESULT;
    }

    totalDigits = toDigitArray(n);

    int pivot = pivot();
    if (pivot == NO_RESULT) {
      return NO_RESULT;
    }

    int minValue = Integer.MAX_VALUE;
    int minIndex = -1;
    for (int i = pivot - 1; i >= 0; i--) {
      if (digits[i] > digits[pivot] && digits[i] <= minValue) {
        minValue = digits[i];
        minIndex = i;
      }
    }
    swap(minIndex, pivot);

    // reverse
    for (int i = 0; i < pivot / 2; i++) {
      swap(i, pivot - 1 - i);
    }

    if (digits[9] > 1) {
      return NO_RESULT;
    }

    return arrayToNumber();
  }
}
