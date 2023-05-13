package cn.jackcraft.runforyourlife.literature;

import static cn.jackcraft.runforyourlife.RunForYourLife.DEFAULT_SYSTEM_LOCALE;

public class StringPool {

    public StringPool() {}

    public enum TextSelections {
        welcome,
        placeholderAPINotFound,
        versionNotMatched,
        bye
    }

    public enum LanguageSelection {
        SimplifiedChinese,
        English
    }

    public String getFromPool(TextSelections select) {
        return (isSimplifiedChinese()
                ? new SimplifiedChinese().getFromPool(select)
                : new English().getFromPool(select));
    }

    public String getFromPool(TextSelections select, LanguageSelection lang) {
        switch (lang) {
            case English:
                return new English().getFromPool(select);
            case SimplifiedChinese:
            default:
                return new SimplifiedChinese().getFromPool(select);
        }
    }

    public static boolean isSimplifiedChinese() {
        return (DEFAULT_SYSTEM_LOCALE.getLanguage().equals("zh") && DEFAULT_SYSTEM_LOCALE.getCountry().equals("CN"));
    }

    public static String langSelect(String chineseLangString, String englishLangString) {
        return (isSimplifiedChinese()
                ? chineseLangString
                : englishLangString);
    }

    public static class SimplifiedChinese extends StringPool {
        public SimplifiedChinese() {
            super();
        }

        private final String welcome = "欢迎使用 逃往生处 插件！";
        private final String placeholderAPINotFound = "没有找到 PlaceholderAPI 插件，PlaceholderAPI 变量将不会启用！";
        private final String versionNotMatched = "[RunForYourLife] 配置文件版本与当前插件支持配置文件版本不一，为了安全起见，将不会加载插件！\n" +
                "我们建议先将当前配置文件备份一份，再删除旧配置文件重新启动服务器生成一份新的，使用备份的旧配置对生成的新配置进行修改。";
        private final String bye = "感谢使用 逃往生处，已卸载插件，期待下次再见！";

        protected final String[] pool = new String[] {
                welcome,
                placeholderAPINotFound,
                versionNotMatched,
                bye
        };

        @Override
        public String getFromPool(TextSelections select) {
            return this.pool[select.ordinal()];
        }
    }

    public static class English extends StringPool {
        public English() {
            super();
        }

        public final String welcome = "You're using plugin \"Run for your life\", Hooray!";
        public final String placeholderAPINotFound = "Cannot find plugin \"PlaceholderAPI\". Variable for \"PlaceholderAPI\" is now disabled!";
        public final String versionNotMatched = "[RunForYourLife] Configuration file holds a different version to describe this plugin.\n\"Run For Your Life\" will NOT be loaded due to the safety reason.\n" +
                "We suggest you ought to have a backup for your previous configurations. Remove the old ones to let server have a newer version generated.\n" +
                "Then, you might have the newer one modified manually.";
        private final String bye = "Plugin \"Run for your life\" unloaded. Thanks for using, see you next time!";

        protected final String[] pool = new String[] {
                welcome,
                placeholderAPINotFound,
                versionNotMatched,
                bye
        };

        @Override
        public String getFromPool(TextSelections select) {
            return this.pool[select.ordinal()];
        }
    }
}