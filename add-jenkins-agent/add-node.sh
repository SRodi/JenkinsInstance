#!/usr/bin/env bash

echo "=============GET AGENT-NODE DETAILS================================="
echo "enter host machine ip address.."; read host_ip
export ip=$host_ip
echo "enter port.."; read port
export port=$port
echo "enter node name.."; read node_name
export node_name=$node_name
echo "enter remote root folder.."; read root_dir
export dir=$root_dir
echo "enter description.."; read description
export node_description=$description
echo "enter number of executors.."; read executors
export num_executors=$executors
echo "enter label string.."; read label
export node_label=$label



echo "=============CREATE GROOVY SCRIPT==================================="
echo "creating a goovy script to add this agent to Jenkins master node.."
# create envirmonment variable with master-node IP passed as argument
export master_ip=$1
# create the inventory file with master-node IP
envsubst < playbooks/inventory/blueprint > tmp/host
# create a groovy script with new node-agent details
envsubst < groovy/addNode.groovy > tmp/$node_name.groovy



echo "=============COPY GROOVY SCRIPT====================================="
echo "copying script to remote Jenkins master node"
echo "enter ssh password for Jenkins master node.."

# ad-hoc Ansible commands to copy groovy script to remote /var/lib/jenkins/init.groovy.d

# ============= PUBLIC IP 
ansible $1 -m copy -a "src=tmp/$node_name.groovy dest=/var/lib/jenkins/init.groovy.d" -k -u jenkins

# ============= LOCAL IP (modify /etc/ansible/hosts file and add local IP under [test] group) 
# ansible test -m copy -a "src=tmp/$node_name.groovy dest=/var/lib/jenkins/init.groovy.d" -k -u jenkins



echo "=============RESTART JENKINS SERVICE================================"
echo "enter ssh password for Jenkins master node.."

# restart jenkins server
cd playbooks
ansible-playbook -i ../tmp/host --limit master restartJenkins.yml -k -u jenkins --extra-vars "ansible_sudo_pass=jenkins"



echo "=============DELETE .groovy SCRIPT FROM init.groovy.d folder========"
# wait for jenkins service to fully restart..
sleep 35
ansible $1 -m file -a "path=/var/lib/jenkins/init.groovy.d/$node_name.groovy state=absent" -k -u jenkins



echo "=============DELETE ALL FILES FROM tmp DIRECTORY===================="
cd ../tmp && rm -r $node_name.groovy host