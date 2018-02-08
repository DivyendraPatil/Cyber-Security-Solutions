
import hashlib

w = open('rockyou.txt', 'r')

for line1 in w:
	for line2 in open('hex.txt','r'):	
		if (hashlib.sha256(line1.strip('\n').encode()).hexdigest() == line2.strip('\n')):
			print("Found",line1,"for",line2)
h.close()
w.close()

#hash_object = hashlib.sha256(b'hello')
#hex_dig = hash_object.hexdigest()
#print(hex_dig)

#print(hashlib.sha256('hello'.encode()).hexdigest())

# hello = 2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824
