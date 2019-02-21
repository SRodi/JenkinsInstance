# Goal: create and provision jenkins agent-node machines

# to speed up 2 VMs and provision jenkinsagent-nodes

vagrant up

# execute playbook (provision an existing machine)

ansible-playbook -i inventory/hosts --limit agent-nodes site.yml -k -u {{ remote_username }}

# requirements:
	
	- Vagrantfile needs to be updated with guest machine IP addresses

	- add IPs to agent-nodes field in inventory/hosts inventory file

	- make sure master-node puclic key is correctly stored in file /master_public_key/master_node_key