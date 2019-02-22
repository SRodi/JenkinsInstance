# Goal: programmatically add and configure an agent-node to Jenkins master instance

# to run script
sudo bash add-node.sh {{ master-node IP }}

# requirements:
	
	1. jenkins instance must have global-scope, "SSH Username with private key" credentials with ID:"MasterNode" and private key entered directly. (upddate groovy/addNode.groovy if another ID is used). 

	2. it is necessary to SSH from master-node machine to agent-node machine to add master to the list of known_hosts

	2. ssh password for Jenkins user in remote agent-machine is required to copy script, restart jenkins service and delete .groovy script on remote master (default: "jenkins")