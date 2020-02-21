## pingscan.py

#!/usr/bin/python
import json
import sys
import os
import nmap

def pingscan(h):
    nm = nmap.PortScanner()
    r = nm.scan(hosts=h, arguments='-n -sn --host-timeout 5')
    result={}
    for ip,content in r["scan"].items():
        addrs = content.get("addresses",{})
        result[ip]=addrs.get("mac","")
    return json.dumps(result)

def scanports(hosts, ports):
    try : 
        nm = nmap.PortScanner()
    except nmap.PortScannerError :
        print('nmap not found',sys.exc_info()[0])
        sys.exit()
    except :
        print('unexpected error:',sys.exc_info()[0])
        sys.exit()
    try:
        if ports.strip():
            arg_scan = ' -v -sS -sU -p ' +  ports
        else:
            arg_scan = ' -v -sS -sU'
        nm.scan(hosts=hosts,arguments=arg_scan)
    except Exception as e:
        print('scan error:'+str(e))
    result = []
    lhosts = nm.all_hosts()
    lhosts = sorted(lhosts, key = lambda x: int(''.join((lambda a:lambda v:a(a,v))(lambda s,x: x if len(x) == 3 else s(s, '0'+x))(i) for i in x.split('.'))))
    for host in lhosts:
        hostinfo = {}
        hostinfo['host'] = host
        hostinfo['state'] = nm[host].state()
        hostinfo['scan'] = []                
        for proto in nm[host].all_protocols():
            portsinfo = {}
            portsinfo['protocol'] = proto
            lport = nm[host][proto].keys() 
            lport.sort(key=int)             

            ports = []
            for port in lport:
                portinfo = {}
                portinfo['port'] = port
                portinfo['state'] = nm[host][proto][port]['state']
                portinfo['service'] = nm[host][proto][port]['name']
                ports.append(portinfo )
            portsinfo['ports'] = ports 
            hostinfo['scan'].append(portsinfo)
        result.append(hostinfo)
    return json.dumps(result)

def scanportstcp(hosts, ports):
    try : 
        nm = nmap.PortScanner()
    except nmap.PortScannerError :
        print('nmap not found',sys.exc_info()[0])
        sys.exit()
    except :
        print('unexpected error:',sys.exc_info()[0])
        sys.exit()
    try:
        if ports.strip():
            arg_scan = ' -v -sS -p ' +  ports
        else:
            arg_scan = ' -v -sS'
        nm.scan(hosts=hosts,arguments=arg_scan)
    except Exception as e:
        print('scan error:'+str(e))
    result = []
    lhosts = nm.all_hosts()
    lhosts = sorted(lhosts, key = lambda x: int(''.join((lambda a:lambda v:a(a,v))(lambda s,x: x if len(x) == 3 else s(s, '0'+x))(i) for i in x.split('.'))))
    for host in lhosts:
        hostinfo = {}
        hostinfo['host'] = host
        hostinfo['state'] = nm[host].state()
        hostinfo['scan'] = []                
        for proto in nm[host].all_protocols():
            portsinfo = {}
            portsinfo['protocol'] = proto
            lport = nm[host][proto].keys() 
            lport.sort(key=int)             

            ports = []
            for port in lport:
                portinfo = {}
                portinfo['port'] = port
                portinfo['state'] = nm[host][proto][port]['state']
                portinfo['service'] = nm[host][proto][port]['name']
                ports.append(portinfo )
            portsinfo['ports'] = ports 
            hostinfo['scan'].append(portsinfo)
        result.append(hostinfo)
    return json.dumps(result)

def scanportsudp(hosts, ports):
    try : 
        nm = nmap.PortScanner()
    except nmap.PortScannerError :
        print('nmap not found',sys.exc_info()[0])
        sys.exit()
    except :
        print('unexpected error:',sys.exc_info()[0])
        sys.exit()
    try:
        if ports.strip():
            arg_scan = ' -v -sU -p ' +  ports
        else:
            arg_scan = ' -v -sU'
        nm.scan(hosts=hosts,arguments=arg_scan)
    except Exception as e:
        print('scan error:'+str(e))
    result = []
    lhosts = nm.all_hosts()
    lhosts = sorted(lhosts, key = lambda x: int(''.join((lambda a:lambda v:a(a,v))(lambda s,x: x if len(x) == 3 else s(s, '0'+x))(i) for i in x.split('.'))))
    for host in lhosts:
        hostinfo = {}
        hostinfo['host'] = host
        hostinfo['state'] = nm[host].state()
        hostinfo['scan'] = []                
        for proto in nm[host].all_protocols():
            portsinfo = {}
            portsinfo['protocol'] = proto
            lport = nm[host][proto].keys() 
            lport.sort(key=int)             

            ports = []
            for port in lport:
                portinfo = {}
                portinfo['port'] = port
                portinfo['state'] = nm[host][proto][port]['state']
                portinfo['service'] = nm[host][proto][port]['name']
                ports.append(portinfo )
            portsinfo['ports'] = ports 
            hostinfo['scan'].append(portsinfo)
        result.append(hostinfo)
    return json.dumps(result)
