package com.example.dell.fintechproject.utils;

//@SuppressLint("ValidFragment")
//public class KeyBoardFragment extends Fragment {
//    MyEditText editText;
//    ConstraintLayout keyboardLayout;
//    FrameLayout frameLayout;
//    Context context;
//    private OnActionListener listener;
//    int[] keyLayoutId = new int[]{R.id.tv_number_1, R.id.tv_number_2, R.id.tv_number_3, R.id.tv_number_0, R.id.tv_number_5, R.id.tv_number_6, R.id.tv_number_7,
//            R.id.tv_number_8, R.id.tv_number_9, R.id.tv_number_000, R.id.tv_number_0, R.id.iv_delete};
//
//    public KeyBoardFragment(FrameLayout context) {
//        this.frameLayout = context;
//    }
//
//    public void init(Context context, MyEditText editText, String pattern) {
//        this.editText = editText;
//        this.context = context;
//        hideSoftKeyboard();
//        this.editText.setCursorVisible(true);
//        this.editText.setPattern(pattern);
//        this.listener = editText.getListener();
//        this.listener.showKeyboard(editText, frameLayout);
//
//        this.frameLayout.setVisibility(View.VISIBLE);
//        //this.imeBtn.setText(this.editText.ime);
//    }
//
//    public void init(Context context, MyEditText editText, String pattern, List<Currency> currencyList, ListView favoriteCurrencyListView) {
//        this.editText = editText;
//        this.context = context;
//        hideSoftKeyboard();
//        this.editText.setCursorVisible(true);
//        this.editText.setPattern(pattern);
//        this.listener = editText.getListener();
//        this.listener.showKeyboard(editText, frameLayout);
//
//        this.frameLayout.setVisibility(View.VISIBLE);
//
//        this.editText.setListCurrency(currencyList);
//        this.editText.setFavoriteCurrencyListView(favoriteCurrencyListView);
//
//        //this.imeBtn.setText(this.editText.ime);
//    }
//
//    private void initKeyboardHeight(int keyboardHeight) {
//        int oneKeyHeight = keyboardHeight / 4;
//
//        for (int i = 0; i < keyboardLayout.getChildCount(); i++) {
//            final View v = keyboardLayout.getChildAt(i);
//            if (v instanceof TextView) {
//                v.getLayoutParams().height = oneKeyHeight;
//            }
//        }
//    }
//
//    public void hideSoftKeyboard() {
//        InputMethodManager imm = (InputMethodManager) context
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_keyboard_test, null, false);
//        keyboardLayout = (ConstraintLayout) view.findViewById(R.id.fragment_keyboard_test);
//
//        for (int i = 0; i < keyLayoutId.length; i++) {
//            View v = view.findViewById(keyLayoutId[i]);
//            if (v instanceof TextView || v instanceof RelativeLayout) {
//                v.getLayoutParams().height = frameLayout.getLayoutParams().height / 4;
//
//                final String tag = v.getTag().toString();
//                v.setOnLongClickListener(viewListener -> {
//                    switch (tag) {
//                        case "10":
//                            listener.clickDeleteAll(editText);
//                            break;
//                        default:
//                            break;
//                    }
//                    return true;
//                });
//
//                v.setOnClickListener(viewListener -> {
//                    switch (tag) {
//                        case "10":
//                            listener.clickDelete(editText);
//                            break;
//                        case "11":
//                            listener.clickIMEBtn(editText, frameLayout);
//                            break;
//                        case "12": //000
//                            listener.clickNumber(((TextView) v).getText().toString(), editText);
//                            break;
//                        case "13":
//                            break;
//                        case "14":
//                            break;
//                        case "15":
//                            break;
//                        default:
//                            listener.clickNumber(((TextView) v).getText().toString(), editText);
//                            break;
//                    }
//                });
//            }
//        }
//        return view;
//    }
//
//    public void hideKeyboard(MyEditText editText) {
//        editText.setCursorVisible(false);
//        editText.clearFocus();
//        frameLayout.setVisibility(View.GONE);
//    }
//
//    public interface OnActionListener {
//        void showKeyboard(MyEditText currentEditText, FrameLayout keyboardLayout);
//
//        void clickNumber(String s, MyEditText currentEditText);
//
//        void clickDeleteAll(MyEditText currentMyEditText);
//
//        void clickDelete(MyEditText currentEditText);
//
//        void clickIMEBtn(MyEditText currentEditText, FrameLayout frameLayout);
//    }
//}
