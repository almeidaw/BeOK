package beok.beok;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends PreferenceActivity {
    private static List<String> fragments = new ArrayList<String>();


    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preferences_headers, target);
        fragments.clear();
        for (Header header : target) {
            fragments.add(header.fragment);
        }
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return fragments.contains(fragmentName);
    }


    public static class PersonalInfoPreference extends PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_personal_info);
        }

    }

    public static class MotivationPreferences extends PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_motivation);
        }
    }

    public static class GoalsPreferences extends PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_goals);
        }

    }

    public static class EmergencyActionsPreferences extends PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_emergency_actions);
        }

    }

    public static class EmergencyContactsPreferences extends PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_emergency_contacts);
        }

    }

    public static class NotificationsPreferences extends PreferenceFragment{

        CheckBoxPreference notificationEnabled, notificationVibrates;
        ListPreference notificationDay;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_notifications);

            //atribuindo valores as preferencias
            notificationEnabled = (CheckBoxPreference) findPreference("notification_enabled");
            notificationVibrates = (CheckBoxPreference) findPreference("notification_vibration");
            notificationDay = (ListPreference) findPreference("notification_week_day");


            //definindo ações quando as preferencias têm seus valores alterados
            notificationEnabled.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    if (notificationEnabled.isChecked()){
                        Toast.makeText(getActivity(), "notificações serão enviadas", Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(getActivity(), "notificações não serão enviadas", Toast.LENGTH_LONG).show();

                    }
                    return true;
                }
            });

            notificationVibrates.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    if (notificationVibrates.isChecked()){
                        Toast.makeText(getActivity(), "notificações devem vibrar", Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(getActivity(), "notificações não devem vibrar", Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
            });

            notificationDay.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    String diaValor = notificationDay.getValue();
                    String diaEscolhido = notificationDay.getEntry().toString();
                    Toast.makeText(getActivity(), "você escolheu " + diaEscolhido + ", de valor" + diaValor, Toast.LENGTH_SHORT).show();
                return true;
                }
            });


            //fim das definições dos listener para essas preferências
        }

    }

}
