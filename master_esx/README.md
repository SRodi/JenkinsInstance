# Goal: create and provision jenkins master-node machine

# to speed up a VM and provision jenkins master-node 

vagrant up

# execute

ansible-playbook -i inventory/hosts site.yml --limit master-node -k -u {{ remote_username }}

# requirements:

	1. add master-machine IP to Vagrantfile (if using vagrant to create VM) 

	2. modify inventory/hosts with host machine ip
