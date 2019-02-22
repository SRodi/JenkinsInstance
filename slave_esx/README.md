# Goal: create and provision jenkins agent-node machines

# to speed up 2 VMs and provision jenkinsagent-nodes

vagrant up

# execute playbook (provision an existing machine)

ansible-playbook -i inventory/hosts --limit agent-nodes site.yml -k -u {{ remote_username }}

# requirements:
	
	1. Vagrantfile needs to be updated with guest machine IP addresses (if using vagrant to create VMs)

	2. agent-nodes IPs to be added in inventory/hosts file

	3. correct master-node puclic key must be stored in master_public_key/master_node_key