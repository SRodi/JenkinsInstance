# Goal: create and provision jenkins master-node machine

# to speed up a VM and provision jenkins master-node 

vagrant up

# execute

ansible-playbook -i inventory/hosts site.yml --limit master-node -k -u {{ remote_username }}

# requirements:

	- modify inventory/hosts with host machine ip

	- modify roles/jenkins_plugins/vars/main.yml with host IP
