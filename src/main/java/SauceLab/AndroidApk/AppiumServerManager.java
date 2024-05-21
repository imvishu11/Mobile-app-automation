package SauceLab.AndroidApk;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;

/**
 * The class {@link AppiumServerManager} holds static methods to manage the
 * appium server.
 */
public final class AppiumServerManager {

	private static AppiumDriverLocalService server;

	private static final List<ServerArgument> SERVER_ARGS = Arrays.asList(GeneralServerFlag.SESSION_OVERRIDE,
			GeneralServerFlag.LOG_TIMESTAMP);
	private static final Map<ServerArgument, String> SERVER_ARGS_MAP = ImmutableMap.of(GeneralServerFlag.LOG_LEVEL,
			"error:debug");

	private AppiumServerManager() {
	}

	/**
	 * Method to start the appium server.
	 * 
	 * @param ipAddress:     IP address as a String
	 * @param serverArgs:    Appium server arguments to be provided as a List
	 * @param serverArgsMap: Appium server arguments to be provided as a Map
	 */
	public static void startServer(final String ipAddress, final List<ServerArgument> serverArgs,
			final Map<ServerArgument, String> serverArgsMap) {

		final AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder().withArgument(() -> "--base-path",
				"/wd/hub");

		serviceBuilder.withArgument(GeneralServerFlag.RELAXED_SECURITY);

		String[] APPIUM_PATH = TerminalCommandExecutor.executeCommands("which appium");
		serviceBuilder.withAppiumJS(new File(APPIUM_PATH[0].trim()));

		String[] NODE_PATH = TerminalCommandExecutor.executeCommands("which node");
		serviceBuilder.usingDriverExecutable(new File(NODE_PATH[0].trim()));

		serviceBuilder.withIPAddress(ipAddress);
		serviceBuilder.usingAnyFreePort();

		if (serverArgs != null) {
			for (final ServerArgument serverArg : serverArgs)
				serviceBuilder.withArgument(serverArg);
		}
		if (serverArgsMap != null) {
			for (final ServerArgument serverArg : serverArgsMap.keySet())
				serviceBuilder.withArgument(serverArg, serverArgsMap.get(serverArg));
		}

		server = AppiumDriverLocalService.buildService(serviceBuilder);
		server.start();
		System.out.println("Started appium server at address: " + getServiceUrl());
	}

	/**
	 * Method to start the appium server on any free port with defaults defined in
	 * this class.
	 */
	public static void startServerUsingAnyFreePort() {
		startServer(AppiumServiceBuilder.BROADCAST_IP4_ADDRESS, SERVER_ARGS, SERVER_ARGS_MAP);
	}

	/**
	 * Method to stop the appium server.
	 */
	public static void stopServer() {
		if(server!=null) {
			server.stop();
			System.out.println("Killed appium server.");
		}
		
	}

	/**
	 * Method to fetch the URL on which the appium server is running.
	 * 
	 * @return {@link URL} of the appium server.
	 */
	public static URL getServiceUrl() {
		return server.getUrl();
	}
}