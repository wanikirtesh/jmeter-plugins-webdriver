package com.googlecode.jmeter.plugins.webdriver.config.gui;

import com.googlecode.jmeter.plugins.webdriver.config.ChromeDriverConfig;
import kg.apc.jmeter.JMeterPluginsUtils;
import org.apache.jmeter.gui.util.HorizontalPanel;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.testelement.TestElement;

import javax.swing.*;

public class ChromeDriverConfigGui extends WebDriverConfigGui {

    private static final long serialVersionUID = 100L;
    JTextField chromeServicePath;
    JTextField chromeIsHeadless;
    JCheckBox androidEnabled;
   // private JCheckBox headlessEnabled;
    private JCheckBox insecureCertsEnabled;

    @Override
    public String getStaticLabel() {
        return JMeterPluginsUtils.prefixLabel("Chrome Driver Config");
    }

    @Override
    public String getLabelResource() {
        return getClass().getCanonicalName();
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if(element instanceof ChromeDriverConfig) {
            ChromeDriverConfig config = (ChromeDriverConfig)element;
            chromeServicePath.setText(config.getChromeDriverPath());
            androidEnabled.setSelected(config.isAndroidEnabled());
            chromeIsHeadless.setText(config.isHeadlessEnabled());
            getInsecureCertsEnabled().setSelected(config.isInsecureCertsEnabled());
        }
    }

    @Override
    public TestElement createTestElement() {
        ChromeDriverConfig element = new ChromeDriverConfig();
        modifyTestElement(element);
        return element;
    }

    @Override
    public void modifyTestElement(TestElement element) {
        super.modifyTestElement(element);
        if(element instanceof ChromeDriverConfig) {
            ChromeDriverConfig config = (ChromeDriverConfig)element;
            config.setChromeDriverPath(chromeServicePath.getText());
            config.setAndroidEnabled(androidEnabled.isSelected());
            config.setHeadlessEnabled(chromeIsHeadless.getText());
            config.setInsecureCertsEnabled(getInsecureCertsEnabled().isSelected());
        }
    }

    @Override
    public void clearGui() {
        super.clearGui();
        chromeServicePath.setText("");
        androidEnabled.setSelected(false);
       // getHeadlessEnabled().setSelected(false);
        getInsecureCertsEnabled().setSelected(false);
        chromeIsHeadless.setText("");
    }

    @Override
    protected JPanel createBrowserPanel() {
        return createServicePanel();
    }

    @Override
    protected String browserName() {
        return "Chrome";
    }

    @Override
    protected String getWikiPage() {
        return "ChromeDriverConfig";
    }

    private JPanel createServicePanel() {
        final JPanel browserPanel = new VerticalPanel();
        final JPanel chromeServicePanel = new HorizontalPanel();
        final JPanel chromeHeadlessPanel = new HorizontalPanel();
        final JLabel chromeDriverServiceLabel = new JLabel("Path to Chrome Driver");
        final JLabel chromeDriverHeadlessLabel = new JLabel("Use Chrome in Headless Mode (true/false)");
        chromeServicePanel.add(chromeDriverServiceLabel);

        chromeServicePath = new JTextField();
        chromeServicePanel.add(chromeServicePath);
        browserPanel.add(chromeServicePanel);


        chromeHeadlessPanel.add(chromeDriverHeadlessLabel);
        chromeIsHeadless = new JTextField();
        chromeHeadlessPanel.add(chromeIsHeadless);
        browserPanel.add(chromeHeadlessPanel);

        androidEnabled = new JCheckBox("Use Chrome on Android");
        browserPanel.add(androidEnabled);

       // headlessEnabled = new JCheckBox("Use Chrome headless mode");
     //   browserPanel.add(getHeadlessEnabled());

        insecureCertsEnabled = new JCheckBox("Allow Insecure Certs");
        browserPanel.add(getInsecureCertsEnabled());
        return browserPanel;
    }

	@Override
	protected boolean isProxyEnabled() {
		return true;
	}

	@Override
	protected boolean isExperimentalEnabled() {
		return true;
	}

    public JTextField getHeadlessEnabled() {
        return chromeIsHeadless;
    }
    
    public JCheckBox getInsecureCertsEnabled() {
        return insecureCertsEnabled;
    }
}
