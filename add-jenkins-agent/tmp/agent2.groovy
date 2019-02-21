import hudson.model.*
import jenkins.model.*
import hudson.slaves.*
import hudson.slaves.EnvironmentVariablesNodeProperty.Entry

/**
 * INSERT "Launch Method" SNIPPET HERE
 */
import hudson.plugins.sshslaves.verifiers.*

// Pick one of the strategies from the comments below this line
SshHostKeyVerificationStrategy hostKeyVerificationStrategy = new KnownHostsFileKeyVerificationStrategy()
    //= new KnownHostsFileKeyVerificationStrategy() // Known hosts file Verification Strategy
    //= new ManuallyProvidedKeyVerificationStrategy("<your-key-here>") // Manually provided key Verification Strategy
    //= new ManuallyTrustedKeyVerificationStrategy(false /*requires initial manual trust*/) // Manually trusted key Verification Strategy
    //= new NonVerifyingKeyVerificationStrategy() // Non verifying Verification Strategy

// Define a "Launch method": "Launch slave agents via SSH"
ComputerLauncher launcher = new hudson.plugins.sshslaves.SSHLauncher(
        "192.168.60.12", // Host
        22, // Port
        "MasterNode", // Credentials
        (String)null, // JVM Options
        (String)null, // JavaPath
        (String)null, // Prefix Start Slave Command
        (String)null, // Suffix Start Slave Command
        (Integer)null, // Connection Timeout in Seconds
        (Integer)null, // Maximum Number of Retries
        (Integer)null, // The number of seconds to wait between retries
        hostKeyVerificationStrategy // Host Key Verification Strategy
)
/**
 * END "Launch Method"
 */

// Define a "Permanent Agent"
Slave agent = new DumbSlave(
        "agent2", // Name
        "/tmp/agent2-jenkins", // Remote root directory
        launcher)
agent.nodeDescription = "agent2"
agent.numExecutors = 2
agent.labelString = "agent2"
agent.mode = Node.Mode.NORMAL
agent.retentionStrategy = new RetentionStrategy.Always()

// List<Entry> env = new ArrayList<Entry>();
// env.add(new Entry("key1","value1"))
// env.add(new Entry("key2","value2"))
// EnvironmentVariablesNodeProperty envPro = new EnvironmentVariablesNodeProperty(env);

// agent.getNodeProperties().add(envPro)

// Create a "Permanent Agent"
Jenkins.instance.addNode(agent)

return "Node has been created successfully."