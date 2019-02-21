# Goal: Construct a jenkins instance that allows for repeatable deployments, a cloud slave pool, a secure access mechanism and container security.

# Pre-requisites (localhost):
	- ansible 2.7.7
	- python 3.6.7

# this repo contains scripts to: 
	1. deploy Jenkins master node
	2. deploy slave nodes (agent-nodes) 
	3. programmaticaly create agent-nodes in Jenkins master-instance

# Jenkins master&slaves nodes can be created/provisioned through:
	1. vagrant
	
	Vagrant will create Ubuntu 18.04 Virtual Machines and will prompt Ansible to provision with necessary tools
	
	2. ansible
	
	Ansible playbook will provision pre-existing Ubuntu 18.04 machine(s) (VMs or physical)

# Ansible provisioning on Master:

- creates jenkins user with home folder /var/lib/jenkins
- generates ssh keypair and copy public key to local folder
- installs Java and Jenkins
- starts jenkins server
- creates jenkins user admin/admin
- install plugins for jenkins user

# Ansible provisioning on Slaves:

- creates jenkins user
- add master node key to authorized keys
- install Java
- install Docker
- install Maven
- install node

# Jenkins agent-nodes addition to Jenkins instance:

Agent-nodes are created with a bash script which:
- gathers agent-node details
- creates a .groovy scripts 
- copies .groovy script to init.groovy.d folder within Jenkins master-node (scripts withing this folder are executed when Jenkins server restarts)
- prompts the execution of ansible playbook to restart jenkins service



# Known issues
- provisioning is customized and works only for Ubuntu 18.04
- authentication issue when creating agent-nodes
	workaround:
		- ssh into agent-node from master-node

