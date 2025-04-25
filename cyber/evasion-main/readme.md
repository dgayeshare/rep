# Evasion Program Readme

## Evasion Program Overview
Evasion is a Python script designed to encrypt and subsequently decrypt a specified file before executing it. The program takes a single file as an argument, encrypts its content using a simple encryption algorithm, and then waits for a specified time duration before decrypting the file and attempting to execute it. The purpose of this program seems to be introducing a layer of security by encrypting the file contents during storage and only decrypting it just before execution.

**Note: This program should be used for educational or experimental purposes only and should not be used for any malicious activities or unauthorized access to files.**

## Requirements
- Python 3.x

## Usage
To run the Evasion program, use the following command:

```
python3 evasion.py <file>
```

Where `<file>` is the path to the file you want to encrypt, execute, and decrypt.

A small hello world program in C is also provided in the helloc.c file. The user can compile it and test the evasion program on that file, An example is provided below:

```
gcc helloc.c -o hello
python3 evasion.py hello
```

## Algorithm
The program uses a simple encryption and decryption algorithm to modify the contents of the input file.

### Encryption Algorithm
1. The constant `K` is defined as 5.
2. The constant `I` is defined as 100001.
3. The constant `PAD` is defined as 101 * 1024 * 1024 (101 MB).
4. The constant `TIME` is defined as 101 seconds.

The encryption process consists of the following steps:
1. The file size before encryption (`before`) is determined.
2. `PAD` bytes filled with zeroes are appended to the end of the file to ensure a constant size after encryption.
3. The file content is iterated byte by byte.
4. For each byte `b`, the program calculates the encrypted byte value as `[(b + K + (j * I)) % 256]`, where `j` represents the position of the byte in the file.
5. The encrypted byte is written back to the file.

### Decryption Algorithm
The decryption process is the reverse of the encryption process:
1. The file content is iterated byte by byte.
2. For each byte `b`, the program calculates the decrypted byte value as `[(b - K - (j * I)) % 256]`, where `j` represents the position of the byte in the file.
3. The decrypted byte is written back to the file.

## Execution
1. The program takes the specified file as input.
2. It encrypts the file using the encryption algorithm described above.
3. The program then prints "Encrypted file." to indicate the encryption process is complete.
4. The program waits for `TIME` seconds.
5. If the wait time exceeds `TIME`, it decrypts the file using the decryption algorithm.
6. The program prints "Decrypted file." to indicate the decryption process is complete.
7. Finally, the program attempts to execute the decrypted file using the `subprocess.run` method.

## How Antivirus Software Detects Malware
Antivirus software uses various methods to detect and protect against malware. Some common techniques include:
1. **Signature-based Detection**: Antivirus software maintains a database of known malware signatures. It scans files for sequences of bytes that match these signatures. If a file's signature matches a known malware signature, the antivirus program raises an alert.
2. **Heuristic Analysis**: Antivirus software uses heuristics to detect suspicious behavior or code patterns that are indicative of malware. These methods can detect previously unknown or "zero-day" threats based on their behavior.
3. **Behavioral Analysis**: Antivirus software monitors the behavior of programs in real-time. If a program exhibits malicious behavior, such as attempting to modify system files or encrypting user data without permission, it may be flagged as malware.
4. **Sandboxing**: Some antivirus solutions run suspicious files in a virtual environment called a sandbox. This allows the antivirus program to observe the file's behavior without risking damage to the host system. If the file exhibits malicious behavior inside the sandbox, it is considered a threat.
5. **Machine Learning**: Advanced antivirus software uses machine learning algorithms to analyze large datasets and identify new patterns of malware. Machine learning can help detect previously unknown threats by recognizing patterns and anomalies in files and programs.

## Ways to Bypass Malware Detection
1. **Polymorphic Malware**: Malware that changes its code structure and appearance to avoid signature-based detection.
2. **Metamorphic Malware**: Malware that rewrites its own code during each infection, making it difficult to detect using static signatures.
3. **Encrypted Payloads**: Malware that encrypts its payload or communications to evade pattern-based detection.
4. **Fileless Malware**: Malware that resides only in memory, leaving no traces on disk for traditional scanning methods to detect.
5. **Packers and Crypters**: Techniques that compress or encrypt malware to obfuscate its true nature from security software.
6. **Code Obfuscation**: The use of complex code transformations to make the malware's intent and behavior less obvious to static analysis.
7. **Anti-VM Techniques**: Malware designed to detect and evade execution within virtual machine environments often used by security researchers.
8. **DLL Injection**: Malware that injects malicious code into legitimate processes to hide its presence and bypass detection.
9. **Rootkit Techniques**: Malware that alters the operating system's kernel to conceal its presence from security software.
10. **Exploit Kits**: Tools that leverage known software vulnerabilities to deliver malware, making detection challenging.
11. **Living off the Land (LoL) Attacks**: Malware that utilizes legitimate system tools and utilities to blend in with regular network traffic and evade detection.
12. **Zero-day Exploits**: Attacks that target previously unknown vulnerabilities, giving security software little time to develop signatures or detection methods.

## Potential Issues and Improvements
1. The encryption and decryption algorithms used in the program are not strong enough for secure data protection. If the purpose is to ensure a certain level of data obscurity, a stronger encryption algorithm should be used.
2. The program assumes that the file size is constant after the encryption process (due to the `PAD` constant). This assumption might not hold for all scenarios, and a more flexible approach should be considered.
3. The hardcoded constants (`K`, `I`, `PAD`, `TIME`) might not be suitable for all situations, and they could be made configurable through command-line arguments or a configuration file.
4. The program lacks proper error handling. It should include exception handling to gracefully handle errors and provide meaningful error messages to users.
5. Using `subprocess.run` to execute a decrypted file could be risky if the file is untrusted, as it could potentially execute malicious code. Additional security measures, like whitelisting allowed commands or performing additional checks on the file, should be implemented to ensure safe execution.
6. The decryption process occurs after the wait time (`TIME`). In some cases, this might lead to longer execution delays if the decryption process takes significant time. Consider decrypting the file in parallel while waiting to reduce the overall execution time.

## Disclaimer
This program is provided as-is, without any warranty or guarantee. The author of this program is not responsible for any damages or misuse that may occur as a result of using this program. Users are advised to use this program responsibly and legally, adhering to all applicable laws and regulations.