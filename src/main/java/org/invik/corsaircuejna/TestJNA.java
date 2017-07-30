package org.invik.corsaircuejna;

import org.apache.commons.lang3.SystemUtils;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.invik.corsaircuejna.jna.CorsairCUEJNAWrapperLibrary.CorsairLedId;
import static org.invik.corsaircuejna.jna.CorsairCUEJNAWrapperLibrary.CorsairLogicalLayout;

/**
 * Created by Antoine on 08/01/2017.
 */
public class TestJNA {

    private static Map<Integer, CorsairLedId> corsairLedIdAzertyMapping = new HashMap<>();
    private static Map<CorsairLogicalLayout, Map<Integer, CorsairLedId>> baseMappings = new HashMap<>();
    private static Map<Long, CorsairLedPosition> found = new HashMap<>();
    private static CorsairLogicalLayout corsairLogicalLayout;

    private static final int Corsair_VK_Mute = 173;
    private static final int Corsair_VK_ScanNextTrack = 176;
    private static final int Corsair_VK_ScanPreviousTrack = 177;
    private static final int Corsair_VK_Stop = 178;
    private static final int Corsair_VK_PlayPause = 179;
    private static final int Corsair_VK_ApostropheAndDoubleQuote = 192;
    private static final int Corsair_VK_GraveAccentAndTilde = 222;

    static {
        final String arch = SystemUtils.OS_ARCH;
        if (!SystemUtils.IS_OS_WINDOWS) {
            throw new UnsupportedOperationException("Only works on Windows");
        }
        if (arch.equals("amd64")) {
            System.loadLibrary("src/main/resources/lib/win64/CUESDK.x64_2013");
            System.loadLibrary("src/main/resources/lib/win64/CorsairCUEJNAWrapper.x64_2013");
        }
        if (arch.equals("x86")) {
            System.loadLibrary("src/main/resources/lib/win32/CUESDK_2013");
            System.loadLibrary("src/main/resources/lib/win32/CorsairCUEJNAWrapper_2013");
        }
        baseMappings.put(CorsairLogicalLayout.CLL_FR, corsairLedIdAzertyMapping);
        corsairLedIdAzertyMapping.put(Corsair_VK_Mute, CorsairLedId.CLK_Mute);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_ESCAPE, CorsairLedId.CLK_Escape);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F1, CorsairLedId.CLK_F1);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F2, CorsairLedId.CLK_F2);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F3, CorsairLedId.CLK_F3);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F4, CorsairLedId.CLK_F4);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F5, CorsairLedId.CLK_F5);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F6, CorsairLedId.CLK_F6);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F7, CorsairLedId.CLK_F7);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F8, CorsairLedId.CLK_F8);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F9, CorsairLedId.CLK_F9);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F10, CorsairLedId.CLK_F10);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F10, CorsairLedId.CLK_F10);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F11, CorsairLedId.CLK_F11);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F12, CorsairLedId.CLK_F12);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_PRINTSCREEN, CorsairLedId.CLK_PrintScreen);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_SCROLL_LOCK, CorsairLedId.CLK_ScrollLock);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_PAUSE, CorsairLedId.CLK_PauseBreak);
        corsairLedIdAzertyMapping.put(Corsair_VK_Stop, CorsairLedId.CLK_Stop);
        corsairLedIdAzertyMapping.put(Corsair_VK_ScanPreviousTrack, CorsairLedId.CLK_ScanPreviousTrack);
        corsairLedIdAzertyMapping.put(Corsair_VK_PlayPause, CorsairLedId.CLK_PlayPause);
        corsairLedIdAzertyMapping.put(Corsair_VK_ScanNextTrack, CorsairLedId.CLK_ScanNextTrack);
        corsairLedIdAzertyMapping.put(Corsair_VK_GraveAccentAndTilde, CorsairLedId.CLK_GraveAccentAndTilde);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_1, CorsairLedId.CLK_1);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_2, CorsairLedId.CLK_2);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_3, CorsairLedId.CLK_3);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_4, CorsairLedId.CLK_4);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_5, CorsairLedId.CLK_5);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_6, CorsairLedId.CLK_6);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_7, CorsairLedId.CLK_7);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_8, CorsairLedId.CLK_8);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_9, CorsairLedId.CLK_9);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_0, CorsairLedId.CLK_0);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_RIGHT_PARENTHESIS, CorsairLedId.CLK_MinusAndUnderscore);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_EQUALS, CorsairLedId.CLK_EqualsAndPlus);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_BACK_SPACE, CorsairLedId.CLK_Backspace);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_INSERT, CorsairLedId.CLK_Insert);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_HOME, CorsairLedId.CLK_Home);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_PAGE_UP, CorsairLedId.CLK_PageUp);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_DIVIDE, CorsairLedId.CLK_KeypadSlash);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_MULTIPLY, CorsairLedId.CLK_KeypadAsterisk);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_SUBTRACT, CorsairLedId.CLK_KeypadMinus);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_TAB, CorsairLedId.CLK_Tab);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_A, CorsairLedId.CLK_Q);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_Z, CorsairLedId.CLK_W);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_E, CorsairLedId.CLK_E);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_R, CorsairLedId.CLK_R);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_T, CorsairLedId.CLK_T);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_Y, CorsairLedId.CLK_Y);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_U, CorsairLedId.CLK_U);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_I, CorsairLedId.CLK_I);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_O, CorsairLedId.CLK_O);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_P, CorsairLedId.CLK_P);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_DEAD_CIRCUMFLEX, CorsairLedId.CLK_BracketLeft);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_DOLLAR, CorsairLedId.CLK_BracketRight);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_ENTER, CorsairLedId.CLK_Enter);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_DELETE, CorsairLedId.CLK_Delete);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_END, CorsairLedId.CLK_End);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_PAGE_DOWN, CorsairLedId.CLK_PageDown);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_HOME, CorsairLedId.CLK_Home);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_UP, CorsairLedId.CLK_UpArrow);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_PAGE_UP, CorsairLedId.CLK_PageUp);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_ADD, CorsairLedId.CLK_KeypadPlus);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_CAPS_LOCK, CorsairLedId.CLK_CapsLock);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_Q, CorsairLedId.CLK_A);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_S, CorsairLedId.CLK_S);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_D, CorsairLedId.CLK_D);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_F, CorsairLedId.CLK_F);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_G, CorsairLedId.CLK_G);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_H, CorsairLedId.CLK_H);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_J, CorsairLedId.CLK_J);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_K, CorsairLedId.CLK_K);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_L, CorsairLedId.CLK_L);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_M, CorsairLedId.CLK_SemicolonAndColon);
        corsairLedIdAzertyMapping.put(Corsair_VK_ApostropheAndDoubleQuote, CorsairLedId.CLK_ApostropheAndDoubleQuote);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_ASTERISK, CorsairLedId.CLK_NonUsTilde);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_SHIFT, CorsairLedId.CLK_LeftShift);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_LESS, CorsairLedId.CLK_NonUsBackslash);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_W, CorsairLedId.CLK_Z);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_X, CorsairLedId.CLK_X);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_C, CorsairLedId.CLK_C);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_V, CorsairLedId.CLK_V);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_B, CorsairLedId.CLK_B);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_N, CorsairLedId.CLK_N);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_COMMA, CorsairLedId.CLK_M);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_SEMICOLON, CorsairLedId.CLK_CommaAndLessThan);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_COLON, CorsairLedId.CLK_PeriodAndBiggerThan);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_EXCLAMATION_MARK, CorsairLedId.CLK_SlashAndQuestionMark);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_SHIFT, CorsairLedId.CLK_RightShift);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_UP, CorsairLedId.CLK_UpArrow);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_CONTROL, CorsairLedId.CLK_LeftCtrl);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_WINDOWS, CorsairLedId.CLK_LeftGui);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_ALT, CorsairLedId.CLK_LeftAlt);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_SPACE, CorsairLedId.CLK_Space);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_CONTROL, CorsairLedId.CLK_LeftCtrl);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_ALT, CorsairLedId.CLK_RightAlt);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_WINDOWS, CorsairLedId.CLK_RightGui);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_CONTEXT_MENU, CorsairLedId.CLK_Application);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_CONTROL, CorsairLedId.CLK_RightCtrl);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_LEFT, CorsairLedId.CLK_LeftArrow);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_DOWN, CorsairLedId.CLK_DownArrow);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_RIGHT, CorsairLedId.CLK_RightArrow);
        corsairLedIdAzertyMapping.put(KeyEvent.VK_NUM_LOCK, CorsairLedId.CLK_NumLock);
    }

    private static CorsairLedId[] getFromKeyEventCodeAndCorsairLogicalLayout(KeyEvent keyEvent, CorsairLogicalLayout corsairLogicalLayout) {
        /*int modifiers = keyEvent.getModifiers();
        boolean ctrl = (modifiers & InputEvent.CTRL_MASK) != 0;
        EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        AWTEvent awtEvent = eventQueue.peekEvent();
        System.out.println("Next event is KeyEvent ?" + (awtEvent instanceof KeyEvent));
        System.out.println("Next event " + awtEvent);
        if (awtEvent instanceof KeyEvent) {
            KeyEvent nextKeyEvent =  (KeyEvent) awtEvent;
            int modifiersNext = nextKeyEvent.getModifiers();
            boolean ctrlandaltNext = ctrl && (modifiersNext & InputEvent.ALT_MASK) != 0;
            if (ctrl && ctrlandaltNext && keyEvent.getExtendedKeyCode() == KeyEvent.VK_CONTROL) {
                return CorsairLedId.CLK_RightAlt;
            }
        }
        System.out.println("Normal path");*/
        CorsairLedId result = null;
        int code = keyEvent.getKeyCode();
        if (code == KeyEvent.VK_UNDEFINED) {
            long rawCode = 0;
            try {
                Field f = KeyEvent.class.getDeclaredField("rawCode");
                f.setAccessible(true);
                rawCode = (long) f.get(keyEvent);
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return new CorsairLedId[]{baseMappings.get(corsairLogicalLayout).get((int) rawCode)};
        }
        switch (keyEvent.getKeyLocation()) {
            case KeyEvent.KEY_LOCATION_RIGHT:
                switch (code) {
                    case KeyEvent.VK_CONTROL:
                        result = CorsairLedId.CLK_RightCtrl;
                        break;
                    case KeyEvent.VK_SHIFT:
                        result = CorsairLedId.CLK_RightShift;
                        break;
                    case KeyEvent.VK_ALT:
                        result = CorsairLedId.CLK_RightAlt;
                        break;
                    case KeyEvent.VK_WINDOWS:
                        result = CorsairLedId.CLK_RightGui;
                        break;
                }
                break;
            case KeyEvent.KEY_LOCATION_LEFT:
                switch (code) {
                    case KeyEvent.VK_CONTROL:
                        result = CorsairLedId.CLK_LeftCtrl;
                        break;
                    case KeyEvent.VK_SHIFT:
                        result = CorsairLedId.CLK_LeftShift;
                        break;
                    case KeyEvent.VK_ALT:
                        result = CorsairLedId.CLK_LeftAlt;
                        break;
                    case KeyEvent.VK_WINDOWS:
                        result = CorsairLedId.CLK_LeftGui;
                        break;
                }
                break;
            case KeyEvent.KEY_LOCATION_NUMPAD:
                switch (code) {
                    case KeyEvent.VK_ENTER:
                        result = CorsairLedId.CLK_KeypadEnter;
                        break;
                    case KeyEvent.VK_NUMPAD0:
                    case KeyEvent.VK_INSERT:
                        result = CorsairLedId.CLK_Keypad0;
                        break;
                    case KeyEvent.VK_NUMPAD1:
                    case KeyEvent.VK_END:
                        result = CorsairLedId.CLK_Keypad1;
                        break;
                    case KeyEvent.VK_NUMPAD2:
                    case KeyEvent.VK_DOWN:
                        result = CorsairLedId.CLK_Keypad2;
                        break;
                    case KeyEvent.VK_NUMPAD3:
                    case KeyEvent.VK_PAGE_DOWN:
                        result = CorsairLedId.CLK_Keypad3;
                        break;
                    case KeyEvent.VK_NUMPAD4:
                    case KeyEvent.VK_LEFT:
                        result = CorsairLedId.CLK_Keypad4;
                        break;
                    case KeyEvent.VK_NUMPAD5:
                    case KeyEvent.VK_CLEAR:
                        result = CorsairLedId.CLK_Keypad5;
                        break;
                    case KeyEvent.VK_NUMPAD6:
                    case KeyEvent.VK_RIGHT:
                        result = CorsairLedId.CLK_Keypad6;
                        break;
                    case KeyEvent.VK_NUMPAD7:
                    case KeyEvent.VK_HOME:
                        result = CorsairLedId.CLK_Keypad7;
                        break;
                    case KeyEvent.VK_NUMPAD8:
                    case KeyEvent.VK_UP:
                        result = CorsairLedId.CLK_Keypad8;
                        break;
                    case KeyEvent.VK_NUMPAD9:
                    case KeyEvent.VK_PAGE_UP:
                        result = CorsairLedId.CLK_Keypad9;
                        break;
                    case KeyEvent.VK_DECIMAL:
                    case KeyEvent.VK_DELETE:
                        result = CorsairLedId.CLK_KeypadPeriodAndDelete;
                        break;
                    case KeyEvent.VK_ADD:
                        result = CorsairLedId.CLK_KeypadPlus;
                        break;
                    case KeyEvent.VK_SUBTRACT:
                        result = CorsairLedId.CLK_KeypadMinus;
                        break;
                    case KeyEvent.VK_MULTIPLY:
                        result = CorsairLedId.CLK_KeypadAsterisk;
                        break;
                    case KeyEvent.VK_DIVIDE:
                        result = CorsairLedId.CLK_KeypadSlash;
                        break;
                    case KeyEvent.VK_NUM_LOCK:
                        result = CorsairLedId.CLK_NumLock;
                        break;
                }
                break;
            case KeyEvent.KEY_LOCATION_STANDARD:
            default:
                return new CorsairLedId[]{baseMappings.get(corsairLogicalLayout).get(code)};
        }
        return new CorsairLedId[]{result};
    }


    public static void main(String[] args) throws Exception {
        CorsairManager corsairManager = CorsairManager.getInstance();
        corsairManager.initKeyboard();
        int deviceCount = corsairManager.deviceCount();
        System.out.println("Number of found compatible Corsair devices: " + deviceCount);
        final int deviceToUseIndex = corsairManager.getKeyboardDeviceIndex();
        final CorsairDeviceInfo deviceToUse = corsairManager.getKeyboardInfo(deviceToUseIndex);
        corsairLogicalLayout = deviceToUse.getLogicalLayout();
        System.out.println("Device: " + deviceToUse);
        System.out.println("Found a suitable keyboard, starting the process");
        found = corsairManager.getCorsairLedPositions(deviceToUseIndex);

        final List<CorsairLedPosition> values = corsairManager.sortLedPositionsFromTopLeftToBottomRight(found);

        corsairManager.applyLedPositionsColor(Color.GREEN, values.toArray(new CorsairLedPosition[values.size()]));

        JTextField textField = new JTextField();
        textField.setFocusTraversalKeysEnabled(false);

        textField.addKeyListener(new Keychecker());

        JFrame jframe = new JFrame();

        jframe.add(textField);

        jframe.setSize(400, 350);

        jframe.setVisible(true);
    }

    private static class Keychecker extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {
            /*List<Integer> codes = Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_SHIFT);
            int code = event.getExtendedKeyCode();
            if (codes.contains(code)) {
                Pointer<jnaCorsairLedColor> corsairLedColors = Pointer.allocate(jnaCorsairLedColor.class);
                jnaCorsairLedColor jnaCorsairLedColor = new jnaCorsairLedColor();
                jnaCorsairLedColor.ledId();
                setColor(Color.WHITE, jnaCorsairLedColor);
                corsairLedColors.set(jnaCorsairLedColor);
                JNACorsairSetLedsColors(1, corsairLedColors);
            }*/
            long rawCode = 0;
            try {
                Field f = KeyEvent.class.getDeclaredField("rawCode");
                f.setAccessible(true);
                rawCode = (long) f.get(event);
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
                e.printStackTrace();
            }
            CorsairLedId[] corsairLedIds = getFromKeyEventCodeAndCorsairLogicalLayout(event, corsairLogicalLayout);
            CorsairManager corsairManager = CorsairManager.getInstance();
            CorsairLedPosition corsairLedPosition = new CorsairLedPosition(corsairLedIds[0]);
            corsairManager.applyLedPositionsColor(Color.WHITE, corsairLedPosition);
            String code = getVKFromCode(event.getKeyCode());
            String fullCode = code == null ? Integer.toString(event.getKeyCode()) : "KeyEvent." + code;
            if (event.getKeyCode() == KeyEvent.VK_UNDEFINED) {
                System.out.println("corsairLedIdAzertyMapping.put(" + rawCode + ", CorsairLedId." + CorsairLedId.getByValue(corsairLedIds[0].value()) + ");");
            } else {
                System.out.println("corsairLedIdAzertyMapping.put(" + fullCode + ", CorsairLedId." + CorsairLedId.getByValue(corsairLedIds[0].value()) + ");");
            }
        }

        @Override
        public void keyPressed(KeyEvent event) {
            //System.out.println(event);
            List<Integer> codes = Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_SHIFT);
            int code = event.getExtendedKeyCode();
            /*if (codes.contains(code)) {
                CorsairLedId[] corsairLedIds = getFromKeyEventCodeAndCorsairLogicalLayout(event, corsairLogicalLayout);
                Pointer<jnaCorsairLedColor> corsairLedColors = Pointer.allocateArray(jnaCorsairLedColor.class, corsairLedIds.length);
                jnaCorsairLedColor jnaCorsairLedColor = new jnaCorsairLedColor();
                jnaCorsairLedColor.ledId();
                int modifiers = event.getModifiers();
                boolean isCtrl = (modifiers & InputEvent.CTRL_MASK) != 0;
                boolean isAlt = (modifiers & InputEvent.ALT_MASK) != 0;
                boolean isShift = (modifiers & InputEvent.SHIFT_MASK) != 0;
                boolean isCtrlAlt = isCtrl && isAlt;
                boolean isCtrlShift = isCtrl && isShift;
                boolean isAltShift = isAlt && isShift;
                boolean isCtrlAltShit = isCtrlAlt && isShift;
                if (isAlt && !isCtrlAlt && !isAltShift) {
                    setColor(Color.RED, jnaCorsairLedColor);
                }
                if (isCtrl && !isCtrlAlt && !isCtrlShift) {
                    setColor(Color.BLUE, jnaCorsairLedColor);
                }
                if (isShift && !isCtrlShift && !isAltShift) {
                    setColor(Color.GREEN, jnaCorsairLedColor);
                }
                if (isAltShift && !isCtrlAltShit) {
                    setColor(new Color(255, 255, 0), jnaCorsairLedColor);
                }
                if (isCtrlAlt && !isCtrlAltShit) {
                    setColor(new Color(255, 0, 255), jnaCorsairLedColor);
                }
                if (isCtrlShift && !isCtrlAltShit) {
                    setColor(new Color(0, 255, 255), jnaCorsairLedColor);
                }
                corsairLedColors.set(jnaCorsairLedColor);
                JNACorsairSetLedsColors(1, corsairLedColors);
            }*/
            final CorsairManager corsairManager = CorsairManager.getInstance();
            final CorsairLedId[] corsairLedIds = getFromKeyEventCodeAndCorsairLogicalLayout(event, corsairLogicalLayout);
            corsairManager.setLedsColor(Color.WHITE, new CorsairLedColor(corsairLedIds[0]));
        }
    }


    private static String getVKFromCode(int code) {
        final Field[] fields = KeyEvent.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (fieldName.startsWith("VK_")) {
                try {
                    final Integer value = field.getInt(null);
                    if (value == code) {
                        return fieldName;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
