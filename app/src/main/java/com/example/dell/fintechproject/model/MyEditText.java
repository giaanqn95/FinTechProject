package com.example.dell.fintechproject.model;

/**
 * Created by chaum on 7/4/2017.
 */

//public class MyEditText extends AppCompatEditText {
//    public String ime;
//    Context context;
//    int maxLength;
//    private String pattern;
//    private KeyBoardFragment.OnActionListener listener;
//    private int prevCommaAmount;
//    private boolean isFormatting;
//    private String originalText;
//
//    private ClipboardManager myClipboard;
//    private ClipData myClip;
//
//    private List<Currency> currencyList;
//    private ListView favoriteCurrencyListView;
//    private ImageView imgClear;
//
//    private Currency myCurrency;
//
//    public MyEditText(Context context) {
//        super(context);
//        this.context = context;
//        hideSoftKeyboard();
//        myClipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//        setCustomSelection();
//    }
//
//    public MyEditText(Context context, AttributeSet attributeSet) {
//        super(context, attributeSet);
//        this.context = context;
//        hideSoftKeyboard();
//        myClipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//        setCustomSelection();
//    }
//
//    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context = context;
//        hideSoftKeyboard();
//        myClipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//        setCustomSelection();
//    }
//
//    public KeyBoardFragment.OnActionListener getListener() {
//        return listener;
//    }
//
//    public void setListCurrency(List<Currency> currencyList) {
//        this.currencyList = currencyList;
//    }
//
//    public void setFavoriteCurrencyListView(ListView favoriteCurrencyListView) {
//        this.favoriteCurrencyListView = favoriteCurrencyListView;
//    }
//
//    public void setPattern(String pattern) {
//        setInputType(InputType.TYPE_CLASS_TEXT);
//        hideSoftKeyboard();
//        this.pattern = pattern;
//        //this.specialChar = (pattern.contains(",") ? ',' : ' ');
//        switch (pattern) {
//            case "nnn,nnn,nnn":
//                pattern1();
//                break;
//            case "nnnn nnnn nnnn":
//                pattern2();
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void pattern1() {
//        setCursorVisible(true);
//        maxLength = 11;
//        setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
//        listener = new KeyBoardFragment.OnActionListener() {
//            @Override
//            public void showKeyboard(MyEditText currentEditText, FrameLayout keyboardLayout) {
//                hideSoftKeyboard();
//                keyboardLayout.setVisibility(View.VISIBLE);
//                //initOnTouchListener();
//            }
//
//            @Override
//            public void clickNumber(String s, MyEditText currentEditText) {
//                if (getText().toString().length() < maxLength && (getText().toString().length() + s.toString().length()) <= maxLength) {
//                    int length = getText().toString().length();
//                    if (!(length == 0 && s.contains("0"))) {
//                        String oldText = getText().toString();
//                        originalText = getText().toString();
//                        onTextChanged(oldText.substring(0, getSelectionStart()) + s + oldText.substring(getSelectionStart(), length),
//                                getSelectionStart(), getText().toString().length(), getText().toString().length() + s.length());
//                    }
//                }
//            }
//
//            @Override
//            public void clickDeleteAll(MyEditText currentMyEditText) {
//                try {
//                    getText().clear();
//                    prevCommaAmount = 0;
//                    //setSelection(0);
//                } catch (IndexOutOfBoundsException e) {
//                }
//            }
//
//            @Override
//            public void clickDelete(MyEditText currentEditText) {
//                try {
//                    int length = getText().toString().length();
//                    if (length <= 1 && getSelectionStart() != 0) {
//                        getText().clear();
//                        prevCommaAmount = 0;
//                        setSelection(0);
//                        return;
//                    }
//                    int selectionStart = getSelectionStart();
//                    int selectionEnd = getSelectionEnd();
//
//                    String oldText = getText().toString();
//                    String checkComma = oldText.substring(0, getSelectionStart());
//                    if (selectionStart == selectionEnd) {
//                        if (checkComma.charAt(checkComma.length() - 1) == ',')
//                            onTextChanged(oldText.substring(0, selectionStart - 2) + oldText.substring(selectionStart, length),
//                                    selectionStart - 1, getText().toString().length(), 0);
//                        else
//                            onTextChanged(oldText.substring(0, selectionStart - 1) + oldText.substring(selectionStart, length),
//                                    selectionStart - 1, getText().toString().length(), 0);
//                    } else {
//                        if (selectionStart == 0 && selectionEnd == length) {
//                            getText().clear();
//                            prevCommaAmount = 0;
//                            setSelection(0);
//                        } else {
//                            onTextChanged(oldText.substring(0, selectionStart) + oldText.substring(selectionEnd, length),
//                                    selectionStart - 1, getText().toString().length(), 0);
//                        }
//                    }
//                } catch (StringIndexOutOfBoundsException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void clickIMEBtn(MyEditText currentEditText, FrameLayout frameLayout) {
//                setCursorVisible(false);
//                clearFocus();
//                frameLayout.setVisibility(View.GONE);
//            }
//        };
//    }
//
//    private void formatInputPattern1(CharSequence s, int start, int count) {
//        isFormatting = true;
//        String oldText = getText().toString();
//
//        StringBuilder sbResult = new StringBuilder();
//        String result;
//        int newStart = start;
//
//        try {
//            String digitAndDotText = s.toString().replace(",", "").replace(" ", "");
//            int commaAmount = 0;
//            result = MyEditTextUtils.formatWithDecimalPattern1(digitAndDotText);
//            sbResult.append(result);
//
//            // count == 0 indicates users is deleting a text
//            // count == 1 indicates users is entering a text
//            int differentCount = s.length() - oldText.length();
//            int pastedTextLength = pasteTextLength();
//            if (differentCount == 0) { //case user paste their text
//                if (originalText != null)
//                    newStart = (pasteTextString().contains(",") && originalText.length() <= 3) ? getSelectionStart() - 1 : getSelectionStart();
//                if (countResultComma(sbResult, ',') == 2 && pastedTextLength > maxLength / 2) {
//                    newStart += 1;
//                }
//            } else if (differentCount == 3) {
//                if (s.charAt(0) != '0') //check input 0 in prefix
//                    newStart += differentCount;
//            } else {
//                if (s.charAt(0) != '0') { //check input 0 in prefix
//                    if (getSelectionStart() != getSelectionEnd()) { //Delete with selection Start and end
//                        newStart = getSelectionStart();
//                    } else {
//                        newStart += ((count == 0) ? 0 : 1);
//                    }
//                }
//            }
//
//            // calculate comma amount in edit text
//            commaAmount += MyEditTextUtils.getCharOccurance(result, ',');
//
//            // flag to mark whether new comma is added / removed
//            if (commaAmount >= 1 && prevCommaAmount != commaAmount) {
//                newStart += ((count == 0) ? -1 : 1);
//                newStart += (differentCount == 0 && pasteTextString().contains(",") && (pastedTextLength <= maxLength / 2)) ? -1 : 0;
//                prevCommaAmount = commaAmount;
//            }
//
//            // case when deleting without comma
//            if (commaAmount == 0 && count == 0 && prevCommaAmount != commaAmount) {
//                if (getSelectionStart() != getSelectionEnd()) { //Delete with selection Start and end
//                    newStart = getSelectionStart();
//                } else {
//                    newStart -= 1;
//                }
//                prevCommaAmount = commaAmount;
//            }
//
//            setText(sbResult.toString());
//
//            // ensure newStart is within result length
//            if (newStart > sbResult.toString().length()) {
//                newStart = sbResult.toString().length();
//            } else if (newStart < 0) {
//                newStart = 0;
//            }
//
//            setSelection(newStart);
//        } catch (NumberFormatException | IndexOutOfBoundsException e) {
//        }
//    }
//
//    private void pattern2() {
//        setInputType(InputType.TYPE_CLASS_NUMBER);
//        setCursorVisible(true);
//        maxLength = 14;
//        setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
//        listener = new KeyBoardFragment.OnActionListener() {
//            @Override
//            public void showKeyboard(MyEditText currentEditText, FrameLayout keyboardLayout) {
//                hideSoftKeyboard();
//                keyboardLayout.setVisibility(View.VISIBLE);
//                prevCommaAmount = 0;
//            }
//
//            @Override
//            public void clickNumber(String s, MyEditText currentEditText) {
//                originalText = getText().toString();
//                if (getText().toString().length() < maxLength && (getText().toString().length() + s.toString().length()) <= maxLength) {
//                    int length = getText().toString().length();
//                    String oldText = getText().toString();
//                    onTextChanged(oldText.substring(0, getSelectionStart()) + s + oldText.substring(getSelectionStart(), length),
//                            getSelectionStart(), getText().toString().length(), getText().toString().length() + s.length());
//                }
//            }
//
//            @Override
//            public void clickDeleteAll(MyEditText currentMyEditText) {
//                try {
//                    getText().clear();
//                    prevCommaAmount = 0;
//                } catch (IndexOutOfBoundsException e) {
//                }
//            }
//
//            @Override
//            public void clickDelete(MyEditText currentEditText) {
//                try {
//                    int length = getText().toString().length();
//                    if (length <= 1 && getSelectionStart() != 0) {
//                        getText().clear();
//                        prevCommaAmount = 0;
//                        setSelection(0);
//                        return;
//                    }
//                    int selectionStart = getSelectionStart();
//                    int selectionEnd = getSelectionEnd();
//
//                    String oldText = getText().toString();
//                    String checkComma = oldText.substring(0, getSelectionStart());
//                    if (selectionStart == selectionEnd) {
//                        if (checkComma.charAt(checkComma.length() - 1) == ' ')
//                            onTextChanged(oldText.substring(0, selectionStart - 2) + oldText.substring(selectionStart, length),
//                                    selectionStart - 1, getText().toString().length(), 0);
//                        else
//                            onTextChanged(oldText.substring(0, selectionStart - 1) + oldText.substring(selectionStart, length),
//                                    selectionStart - 1, getText().toString().length(), 0);
//                    } else {
//                        if (selectionStart == 0 && selectionEnd == length) {
//                            getText().clear();
//                            prevCommaAmount = 0;
//                            setSelection(0);
//                        } else {
//                            onTextChanged(oldText.substring(0, selectionStart) + oldText.substring(selectionEnd, length),
//                                    selectionStart - 1, getText().toString().length(), 0);
//                        }
//                    }
//                } catch (StringIndexOutOfBoundsException e) {
//                }
//            }
//
//            @Override
//            public void clickIMEBtn(MyEditText currentEditText, FrameLayout frameLayout) {
//                setCursorVisible(false);
//                clearFocus();
//                frameLayout.setVisibility(View.GONE);
//            }
//        };
//    }
//
//    private void formatInputPattern2Test(CharSequence s, int start, int count) {
//        isFormatting = true;
//
//        StringBuilder sbResult = new StringBuilder();
//        String oldText = getText().toString();
//        String result;
//        int newStart = start;
//
//        try {
//            String digitAndDotText = s.toString().replace(" ", "").replace(",", "");
//            int commaAmount = 0;
//            result = MyEditTextUtils.insertPeriodically(digitAndDotText, " ", 4);
//            sbResult.append(result);
//
//            // count == 0 indicates users is deleting a text
//            // count == 1 indicates users is entering a text
//            int differentCount = s.length() - oldText.length();
//            int pastedTextLength = pasteTextLength();
//            if (differentCount == 0) { //case user paste their text
//                newStart = (pasteTextString().contains(" ") && originalText.length() <= 3) ? getSelectionStart() - 1 : getSelectionStart();
//                if (countResultComma(sbResult, ' ') == 2 && pastedTextLength > maxLength / 2) {
//                    newStart += 1;
//                }
//            } else if (differentCount == 3) { //case with 3 zero
//                newStart += differentCount;
//            } else {
//                if (getSelectionStart() != getSelectionEnd()) { //Delete with selection Start and end
//                    newStart = getSelectionStart();
//                } else {
//                    newStart += ((count == 0) ? 0 : 1);
//                }
//            }
//
//            // calculate space amount in edit text
//            commaAmount += MyEditTextUtils.getCharOccurance(result, ' ');
//
//            // flag to mark whether new space is added / removed
//            if (commaAmount >= 1 && prevCommaAmount != commaAmount) {
//                newStart += ((count == 0) ? -1 : 1);
//                newStart += (differentCount == 0 && pasteTextString().contains(" ") && (pastedTextLength <= maxLength / 2)) ? -1 : 0;
//                prevCommaAmount = commaAmount;
//            }
//
//            // case when deleting without space
//            if (commaAmount == 0 && count == 0 && prevCommaAmount != commaAmount) {
//                newStart -= 1;
//                prevCommaAmount = commaAmount;
//            }
//
//            /*if (commaAmount == 2 && originalText.length() == 9 && originalText.charAt(getSelectionStart() + 1) == ' ') {
//                newStart += (count == 0) ? -1 : 1;
//            }*/
//
//            setText(sbResult.toString());
//
//            if (newStart > sbResult.toString().length()) {
//                newStart = sbResult.toString().length();
//            } else if (newStart < 0) {
//                newStart = 0;
//            }
//
//            setSelection(newStart);
//        } catch (NumberFormatException | IndexOutOfBoundsException e) {
//        }
//    }
//
//    private void formatInputPattern2(CharSequence s, int start, int count) {
//        isFormatting = true;
//        String oldText = getText().toString();
//
//        StringBuilder sbResult = new StringBuilder();
//        String result;
//        int pastedTextLength = pasteTextLength();
//        int newStart = start;
//
//        try {
//            String digitAndDotText = s.toString().replace(",", "").replace(" ", "");
//            int commaAmount = 0;
//            result = MyEditTextUtils.formatWithDecimalPattern2(digitAndDotText);
//            sbResult.append(result);
//
//            // count == 0 indicates users is deleting a text
//            // count == 1 indicates users is entering a text
//            int differentCount = s.length() - oldText.length();
//            if (differentCount == 0) { //case user paste their text
//                newStart = (pasteTextString().contains(" ") && originalText.length() <= 4) ? getSelectionStart() - 1 : getSelectionStart();
//                Log.e("pastedTextLength", pastedTextLength + "");
//                if (countResultComma(sbResult, ' ') == 2 && pastedTextLength > maxLength / 3 && pastedTextLength != maxLength / 2) {
//                    newStart += 1;
//                }
//            } else if (differentCount == 3) {
//                if (s.charAt(0) != '0') //check input 0 in prefix
//                    newStart += differentCount;
//            } else {
//                if (s.charAt(0) != '0') { //check input 0 in prefix
//                    if (getSelectionStart() != getSelectionEnd()) { //Delete with selection Start and end
//                        newStart = getSelectionStart();
//                    } else {
//                        newStart += ((count == 0) ? 0 : 1);
//                    }
//                }
//            }
//
//            // calculate space amount in edit text
//            commaAmount += MyEditTextUtils.getCharOccurance(result, ' ');
//
//            // flag to mark whether new space is added / removed
//            if (commaAmount >= 1 && prevCommaAmount != commaAmount) {
//                newStart += ((count == 0) ? -1 : 1);
//                newStart += (differentCount == 0 && pasteTextString().contains(" ") && (pastedTextLength <= maxLength / 2)) ? -1 : 0;
//                prevCommaAmount = commaAmount;
//            }
//
//            // case when deleting without space
//            if (commaAmount == 0 && count == 0 && prevCommaAmount != commaAmount) {
//                if (getSelectionStart() != getSelectionEnd()) { //Delete with selection Start and end
//                    newStart = getSelectionStart();
//                } else {
//                    newStart -= 1;
//                }
//                prevCommaAmount = commaAmount;
//            }
//
//            if (differentCount == 0 && pastedTextLength == 6)
//                newStart -= 1;
//
//            setText(sbResult.toString());
//
//            // ensure newStart is within result length
//            if (newStart > sbResult.toString().length()) {
//                newStart = sbResult.toString().length();
//            } else if (newStart < 0) {
//                newStart = 0;
//            }
//
//            if (differentCount == 0 && originalText.length() == 0)
//                setSelection(getText().length());
//            else
//                setSelection(newStart);
//        } catch (NumberFormatException | IndexOutOfBoundsException e) {
//
//        }
//    }
//
//    public void setIMEText(String text) {
//        hideSoftKeyboard();
//        ime = text;
//    }
//
//    public void hideSoftKeyboard() {
//        InputMethodManager imm = (InputMethodManager) getContext()
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
//    }
//
//    @Override
//    protected void onTextChanged(CharSequence s, int start, int lengthBefore, int lengthAfter) {
//        if (isFormatting) {
//            return;
//        }
//
//        if (s.length() > 0) {
//            switch (pattern) {
//                case "nnn,nnn,nnn":
//                    formatInputPattern1(s, start, lengthAfter);
//                    break;
//                case "nnnn nnnn nnnn":
//                    formatInputPattern2Test(s, start, lengthAfter);
//                    break;
//            }
//        }
//
//        if (s.length() > 0 && currencyList != null) {
//            refreshListCurrency(Double.parseDouble(getText().toString().replace(",", "")));
//            imgClear.setVisibility(View.VISIBLE);
//        } else if (s.length() <= 0 && favoriteCurrencyListView != null) {
//            imgClear.setVisibility(GONE);
//            refreshListCurrency(0);
//        }
//        isFormatting = false;
//    }
//
//    private void refreshListCurrency(double moneyEnter) {
//        AdapterExchangeCurrency adapterExchangeCurrency = new AdapterExchangeCurrency(getContext(), currencyList, moneyEnter, myCurrency);
//        favoriteCurrencyListView.setAdapter(adapterExchangeCurrency);
//    }
//
//    @Override
//    public boolean onTextContextMenuItem(int id) {
//        hideSoftKeyboard();
//        switch (id) {
//            case android.R.id.paste:
//                String oldText = getText().toString();
//                originalText = oldText;
//                int length = oldText.length();
//                String pastedData = pasteTextString();
//
//                if (pastedData.length() + length >= maxLength || pastedData.matches("^[a-z.A-Z]+$") || pastedData.length() > maxLength - 2) {
//                    Toast.makeText(context, "Pasted data too long for this field or invalid input!", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//                break;
//        }
//        return super.onTextContextMenuItem(id);
//    }
//
//    private void setCustomSelection() {
//        hideSoftKeyboard();
//        setCustomSelectionActionModeCallback(new ActionMode.Callback() {
//            @Override
//            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//                hideSoftKeyboard();
//                return true;
//            }
//
//            @Override
//            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
//                return false;
//            }
//
//            @Override
//            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case android.R.id.copy:
//                        copyText(actionMode, 1);
//                        return true;
//                    case android.R.id.cut:
//                        copyText(actionMode, 2);
//                        return true;
//                    case android.R.id.paste:
//                        pasteText(actionMode);
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//
//            @Override
//            public void onDestroyActionMode(ActionMode actionMode) {
//            }
//        });
//    }
//
//    private void copyText(ActionMode actionMode, int type) {
//        int min = 0;
//        int max = getText().length();
//        int length = getText().toString().length();
//        String oldText = getText().toString();
//        if (isFocused()) {
//            final int selStart = getSelectionStart();
//            final int selEnd = getSelectionEnd();
//
//            min = Math.max(0, Math.min(selStart, selEnd));
//            max = Math.max(0, Math.max(selStart, selEnd));
//        }
//
//        final CharSequence selectedText = getText()
//                .subSequence(min, max);
//        String text = selectedText.toString().replace(",", "").replace(" ", "");
//
//        myClip = ClipData.newPlainText("text", text);
//        myClipboard.setPrimaryClip(myClip);
//
//        Toast.makeText(context, (type == 1) ? "Text Copied" : "Text cut",
//                Toast.LENGTH_SHORT).show();
//
//        if (type == 2) {
//            if (getSelectionStart() == 0 && getSelectionEnd() == oldText.length()) {
//                getText().clear();
//                prevCommaAmount = 0;
//                setSelection(0);
//            } else {
//                onTextChanged(oldText.substring(0, getSelectionStart()) + oldText.substring(getSelectionEnd(), length),
//                        getSelectionStart() - 1, getText().toString().length(), 0);
//            }
//        }
//        actionMode.finish();
//    }
//
//    private void pasteText(ActionMode actionMode) {
//        ClipData.Item item = myClipboard.getPrimaryClip().getItemAt(0);
//        CharSequence pastedData = item.getText();
//
//        String oldText = getText().toString();
//        String selectedText = oldText.substring(getSelectionStart(), getSelectionEnd());
//        int length = oldText.length();
//        if (length + pastedData.length() > maxLength - 2 || pastedData.toString().matches("^[a-z.A-Z]+$")) {
//            if (pastedData.length() > maxLength - 2) {
//                Toast.makeText(context, "Pasted data too long for this field or invalid input!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (getSelectionStart() == 0 && getSelectionEnd() == length) {
//                getText().clear();
//                originalText = "";
//                append(pastedData);
//                setSelection(getText().toString().length());
//            } else if (oldText.replaceAll(",", "").replaceAll(" ", "").length() - selectedText.length() + pastedData.length() < maxLength - 2) {
//                pasteInTheMiddle(oldText, pastedData.toString(), length);
//            } else
//                Toast.makeText(context, "Pasted data too long for this field or invalid input!", Toast.LENGTH_SHORT).show();
//        } else {
//            if (getSelectionStart() != getSelectionEnd()) {
//                if (getSelectionStart() == 0 && getSelectionEnd() == length) { //paste all text
//                    pasteAllText(pastedData.toString());
//                } else { //paste in the middle
//                    pasteInTheMiddle(oldText, pastedData.toString(), length);
//                }
//            } else { //paste in the end
//                pasteInTheEnd(oldText, pastedData.toString(), length);
//            }
//        }
//
//        actionMode.finish();
//    }
//
//    private void pasteAllText(String pastedData) {
//        getText().clear();
//        originalText = "";
//        append(pastedData);
//        setSelection(getText().toString().length());
//    }
//
//    private void pasteInTheMiddle(String oldText, String pastedData, int length) {
//        originalText = getText().toString();
//        String chooseText = getText().toString().substring(getSelectionStart(), getSelectionEnd());
//        int selectionEnd = getSelectionEnd();
//        onTextChanged(oldText.substring(0, getSelectionStart()) + pastedData + oldText.substring(getSelectionEnd(), length),
//                getSelectionStart(), getText().toString().length(), getText().toString().length() + pastedData.length());
//        setSelection(selectionEnd + (pastedData.length() - chooseText.length()));
//    }
//
//    private void pasteInTheEnd(String oldText, String pastedData, int length) {
//        onTextChanged(oldText.substring(0, getSelectionStart()) + pastedData + oldText.substring(getSelectionStart(), length),
//                getSelectionStart(), getText().toString().length(), getText().toString().length() + pastedData.length());
//        setSelection(getText().toString().length());
//    }
//
//    private int pasteTextLength() {
//        try {
//            ClipData.Item item = myClipboard.getPrimaryClip().getItemAt(0);
//            CharSequence pastedData = item.getText();
//
//            return pastedData.length();
//        } catch (NullPointerException e) {
//            return 0;
//        }
//    }
//
//    private String pasteTextString() {
//        try {
//            ClipData.Item item = myClipboard.getPrimaryClip().getItemAt(0);
//            CharSequence pastedData = item.getText();
//
//            return pastedData.toString();
//        } catch (NullPointerException e) {
//            return "";
//        }
//    }
//
//    private int countResultComma(StringBuilder result, char charSequence) {
//        int count = 0;
//        for (int i = 0; i < result.toString().length(); i++) {
//            if (result.toString().charAt(i) == charSequence)
//                count++;
//        }
//        return count;
//    }
//
//    @Override
//    protected void onSelectionChanged(int selStart, int selEnd) {
//        super.onSelectionChanged(selStart, selEnd);
//        hideSoftKeyboard();
//    }
//
//    public void setImageClear(ImageView imgClear) {
//        this.imgClear = imgClear;
//
//        this.imgClear.setOnClickListener(v -> {
//            getText().clear();
//            prevCommaAmount = 0;
//
//            refreshListCurrency(0);
//            this.imgClear.setVisibility(View.GONE);
//        });
//    }
//
//    public void setMyCurrency(Currency myCurrency) {
//        this.myCurrency = myCurrency;
//    }
//}