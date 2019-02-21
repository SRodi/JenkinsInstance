# Goal: programmatically add and configure an agent-node to Jenkins master instance

# to run script
bash add-node.sh 

# requirements:
	
	- jenkins instance must have credentials (i've hardcoded "MasterNode" in groovy/addNode.groovy)

	- master-node IP must be added to line 33 of add-node.sh

	- playbook/inventory/hosts file needs to be updated with master-node IP

	- ssh password for jenkins user in remote machine is required to copy script, restart jenkins service and delete script

# known issues:

authentication could fail (jenkins master-node logs). workaround:
	- ssh from master-node to each agent-node 